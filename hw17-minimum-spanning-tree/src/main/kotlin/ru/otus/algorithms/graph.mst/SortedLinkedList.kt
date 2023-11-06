package ru.otus.algorithms.graph.mst

class SortedLinkedList(): Iterable<SortedLinkedList.Item?> {

    var first: Item? = null
        private set

    var size = 0
        private set

    override fun iterator(): ItemIterator {
        return ItemIterator(first)
    }

    fun add(item: Array<Int>) {
        if (first == null) first = Item(item)
        else if (first!!.item[2] >= item[2]) {
            val tail = first
            first = Item(item, tail)
        } else {
            var prev = first
            while (prev?.next != null && prev.next!!.item[2] < item[2]) {
                prev = prev.next
            }
            val next = prev!!.next
            prev.next = Item(item, next)
        }
        size++
    }

    class Item(val item: Array<Int>, var next: Item? = null)

    class ItemIterator(var value: Item?): Iterator<Item?> {
        override fun hasNext(): Boolean {
            return value?.next != null
        }

        override fun next(): Item? {
            val item = value
            value = value?.next
            return item
        }
    }
}
