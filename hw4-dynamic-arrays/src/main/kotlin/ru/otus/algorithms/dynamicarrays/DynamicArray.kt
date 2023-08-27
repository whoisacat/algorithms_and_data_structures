package com.whoisacat.edu.otus.algoriths.dynamicarrays

interface DynamicArray <T> {

    fun add(item: T, index: Int)

    fun remove(index: Int): T?
    fun size(): Int
}