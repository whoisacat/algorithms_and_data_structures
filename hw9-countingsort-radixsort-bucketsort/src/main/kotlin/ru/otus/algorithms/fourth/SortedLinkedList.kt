package ru.otus.algorithms.fourth

class SortedLinkedList(): Iterable<SortedLinkedList.Item?> {

    var first: Item? = null
        private set

    var size = 0
        private set

    override fun iterator(): ItemIterator {
        return ItemIterator(first)
    }

    fun add(new: Int) {
        if (first == null) first = Item(new)
        else if (first!!.value >= new) {
            val tail = first
            first = Item(new, tail)
        } else {
            var prev = first
            while (prev?.next != null && prev.next!!.value < new) {
                prev = prev.next
            }
            val next = prev!!.next
            prev.next = Item(new, next)
        }
        size++
    }

    class Item(val value: Int, var next: Item? = null)

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