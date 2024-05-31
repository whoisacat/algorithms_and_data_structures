package ru.otus.algorithms.text.runlegthencoding

import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

fun rleCompress(input: ByteArray): ByteArray {
    if (input.isEmpty()) return byteArrayOf()

    val result = mutableListOf<Byte>()
    var count = 1
    var prevByte = input[0]

    for (i in 1 until input.size) {
        val currentByte = input[i]
        if (currentByte == prevByte) {
            count++
            if (count == 255) { // Максимальное значение для байта
                result.add(prevByte)
                result.add(count.toByte())
                count = 0
            }
        } else {
            if (count > 0) {
                result.add(prevByte)
                result.add(count.toByte())
            }
            prevByte = currentByte
            count = 1
        }
    }

    if (count > 0) {
        result.add(prevByte)
        result.add(count.toByte())
    }

    return result.toByteArray()
}

fun compressFile(inputFilePath: String, outputFilePath: String) {
    val inputFile = File(inputFilePath)
    println("input file exists ${inputFile.exists()}")
    println("input file size ${inputFile.length() / 1024} kb")
    val outputFile = File(outputFilePath)
    if (outputFile.exists()) outputFile.delete()

    FileInputStream(inputFile).use { inputStream ->
        val inputBytes = inputStream.readBytes()
        val compressedBytes = rleCompress(inputBytes)
        FileOutputStream(outputFile).use { outputStream ->
            outputStream.write(compressedBytes)
        }
    println("output file size ${outputFile.length() / 1024} kb")
    }
}

fun main() {
    val inputFilePath = "/path"
    val outputFilePath = "/path"

    compressFile(inputFilePath, outputFilePath)
    println("File compressed")
}
