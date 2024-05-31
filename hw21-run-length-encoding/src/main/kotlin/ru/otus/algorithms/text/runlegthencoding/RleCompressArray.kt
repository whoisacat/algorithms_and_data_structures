package ru.otus.algorithms.text.runlegthencoding

fun rleCompress(input: Array<Any?>): Array<Any> {
    if (input.isEmpty()) return emptyArray()

    val result = mutableListOf<Any>()
    var count = 1
    var prevItem = input[0]

    for (i in 1 until input.size) {
        val currentItem = input[i]
        if (currentItem == prevItem) {
            count++
        } else {
            result.add(prevItem ?: "null")
            result.add(count)
            prevItem = currentItem
            count = 1
        }
    }

    result.add(prevItem ?: "null")
    result.add(count)

    return result.toTypedArray()
}

fun main() {
    val input = arrayOf<Any?>(1, 1, 1, 2, 2, 3, 3, 3, 3, "a", "a", null, null, null)
    val compressed = rleCompress(input)
    println("Original: ${input.joinToString(", ") { it.toString() }}")
    println("Compressed: ${compressed.joinToString(", ") { it.toString() }}")
}
