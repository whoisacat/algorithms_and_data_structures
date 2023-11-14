package ru.otus.algorithms.text.boyermoore

import kotlin.math.max


fun main(args: Array<String>) {
    println(args)
}

fun fullSearch(text: String, pattern: String): Int {
    var idx = 0
    val checkLength = text.length - pattern.length
    while (idx <= checkLength) {
        for (pidx in pattern.indices) {
            if (pattern[pidx] != text[pidx + idx]) break
            if (pidx == pattern.length - 1) return idx
        }
        idx++
    }
    return Int.MAX_VALUE
}

fun fullSearch2(text: String, pattern: String): Int {
    var idx = 0
    val checkLength = text.length - pattern.length
    while (idx <= checkLength) {
        for (pidx in pattern.length - 1 downTo 0) {
            if (pattern[pidx] != text[pidx + idx]) break
            if (pidx == 0) return idx
        }
        idx++
    }
    return Int.MAX_VALUE
}

fun horspoolSearch(text: String, pattern: String): Int {
    if (pattern.length == 0 || text.length < pattern.length) return Int.MAX_VALUE
    val shiftMatrix = calcShiftMatrix(pattern)
    var idx = 0
    val checkLength = text.length - pattern.length
    while (idx <= checkLength) {
        for (pidx in pattern.length - 1 downTo 0) {
            if (pattern[pidx] != text[pidx + idx]) {
                idx += shiftMatrix[text[pidx + idx].code]
                break
            }
            if (pidx == 0) return idx
        }
    }
    return Int.MAX_VALUE
}

private fun calcShiftMatrix(pattern: String): Array<Int> {
    val shiftMatrix = Array(128) { pattern.length }
    for (idx in pattern.length - 2 downTo 0) {
        if (shiftMatrix[pattern[idx].code] == pattern.length)
            shiftMatrix[pattern[idx].code] = pattern.length - (idx + 1)
    }
    return shiftMatrix
}

fun patternSuffixSearch(text: String, pattern: String): Int {
    if (pattern.isNullOrEmpty()) return Int.MAX_VALUE
    val suffixMatrix = calcSuffixMatrix(pattern)
    val shiftMatrix = calcShiftMatrix(pattern)
    var idx = 0
    val checkLength = text.length - pattern.length
    while (idx <= checkLength) {
        for (pidx in pattern.length - 1 downTo 0) {
            if (pattern[pidx] != text[pidx + idx]) {
                idx += max(shiftMatrix[text[pidx + idx].code], suffixMatrix[pidx - pattern.indices.last])
                break
            }
            if (pidx == 0) return idx
        }
    }
    return Int.MAX_VALUE
}

fun calcSuffixMatrix(pattern: String): Array<Int> {
    val suffixAndPrefixMatching = calcSuffixAndPrefixMatching(pattern)
    val defaultLength =
        if (suffixAndPrefixMatching > 0)
            pattern.length - suffixAndPrefixMatching
        else pattern.indices.last
    val matrix = Array(pattern.length) { defaultLength }
    for (sufLength in 1..pattern.indices.last) {
        val suffix = pattern.subSequence(pattern.indices.last - sufLength, pattern.indices.last)
        for (pidx in pattern.length - 2 downTo sufLength) {
            for (sidx in suffix.indices) {
                if (suffix[sidx] != pattern[pidx - sufLength + sidx]) break
                if (sidx == suffix.indices.last && matrix[sufLength] == defaultLength)
                    matrix[sufLength] = sidx
            }
        }
    }
    return matrix
}

fun calcSuffixAndPrefixMatching(pattern: String): Int {
    if (pattern.length == 0) return 0
    var matchingCount = 0
    for (i in pattern.length / 2 downTo 0)
        if (pattern.subSequence(0..i)
            .equals(pattern.subSequence(pattern.indices.last - i .. pattern.indices.last()))) {
            matchingCount = i
            break
        }
    return matchingCount
}

