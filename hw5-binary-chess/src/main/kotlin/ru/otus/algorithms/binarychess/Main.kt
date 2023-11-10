package ru.otus.algorithms.binarychess

import java.lang.StringBuilder

fun main(args: Array<String>) {
    val positionsIdx = 20
    val king = kingsMove(positionsIdx)
    println("quantity of kings moves: ${king.quantity}")
    println(printChessDeskToString(king.positions))
    val horse = horseMove(positionsIdx)
    println("quantity of horse moves: ${horse.quantity}")
    println(printChessDeskToString(horse.positions))
}

fun kingsMove(idx: Int): ChessMove {
    val position: ULong = 1UL.shl(idx)
    val leftMasked: ULong = position.and(0xFEFEFEFEFEFEFEFEUL)
    val rightMasked: ULong = position.and(0x7F7F7F7F7F7F7F7FUL)
    val moves = leftMasked.shl(7).or(position.shl(8)).or(rightMasked.shl(9))
        .or(leftMasked.shr(1))                              .or(rightMasked.shl(1))
        .or(leftMasked.shr(9)).or(position.shr(8)).or(rightMasked.shr(7))
    return ChessMove(moves, countUpBits1(moves))
}

fun horseMove(idx: Int): ChessMove {
    val position: ULong = 1UL.shl(idx)
    val leftMasked: ULong = position.and(0xFEFEFEFEFEFEFEFEUL)
    val leftLeftMasked: ULong = position.and(0xFCFCFCFCFCFCFCFCUL)
    val rightMasked: ULong = position.and(0x7F7F7F7F7F7F7F7FUL)
    val rightRightMasked: ULong = position.and(0x3F3F3F3F3F3F3F3FUL)
    val moves =
                leftMasked.shl(15).or(rightMasked.shl(17))
        .or(leftLeftMasked.shl(6))            .or(rightRightMasked.shl(10))
        .or(leftLeftMasked.shr(10))           .or(rightMasked.shr(6))
                .or(position.shr(17)).or(rightMasked.shr(15))
    return ChessMove(moves, countUpBits2(moves))
}

fun countUpBits1(moves: ULong): Int {
    var number = moves
    var count = 0
    while (number > 0UL) {
        if (number.and(1U) == 1UL) count++
        number = number.shr(1)
    }
    return count
}

fun countUpBits2(moves: ULong): Int {
    var number = moves
    var count = 0
    while (number > 0UL) {
        count++
        number = number.and(number - 1U)
    }
    return count
}

data class ChessMove(val positions: ULong, val quantity: Int)

fun printChessDeskToString(number: ULong): String {
    val sb = StringBuilder()
    for (i in 7 downTo 0) {
        val n = number.shr(8 * i)
        for (b in 0..7) {
            sb.append(if (n.shr(b).and(1UL) == 1UL) "■  " else "□  ")
        }
        sb.append("\n")
    }
    return sb.toString()
}
