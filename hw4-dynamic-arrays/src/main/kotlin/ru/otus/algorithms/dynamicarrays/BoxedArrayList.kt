package ru.otus.algorithms.dynamicarrays //todo rename in whole rpoject

import com.whoisacat.edu.otus.algoriths.dynamicarrays.DynamicArray
import java.util.*

class BoxedArrayList <T>: DynamicArray<T> {

    private var content: ArrayList<T> = ArrayList()


    override fun add(item: T, index: Int) {
        if (index > content.size) throw Exception("unsupported")
        content.add(index, item)
    }

    override fun remove(index: Int): T {
        return content.removeAt(index)
    }

    override fun size(): Int {
        return content.size
    }
}