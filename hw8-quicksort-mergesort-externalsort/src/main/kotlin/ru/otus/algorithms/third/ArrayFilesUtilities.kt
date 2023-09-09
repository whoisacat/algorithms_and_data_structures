package ru.otus.algorithms.third

import java.io.File
import kotlin.random.Random

class ArrayFilesUtilities(fullFileName: String, withData: Boolean = false) {

    var emty: Boolean = true
    private val file: File

    init {
        file = File(fullFileName)
        if (!file.exists() && !file.createNewFile()) throw Exception("can't create file")
        if (file.exists() && !file.canWrite()) throw Exception("can't write in file")
        if (!withData) file.writeBytes(ByteArray(0))
    }

    // для генерации текстового файла из N строчек, на каждой строке записано случайное число от 1 до T
    fun generateFileWithRandomIntArray(number: Int, intHighLimit: Int) {
        for (i in 0 until number) {
            val int = Random.nextInt(intHighLimit) + 1
            file.appendText("${int}\n")
        }
    }

    fun write(array: Array<Int>) {
        if (!array.isEmpty()) emty = false
        for (i in array.size - 1 downTo 0)
            file.appendText("${array[i]}\n")
    }

    fun getNextNumber(counter: Int): Int {
        return file.readLines() [counter - 1].toInt()
    }
}