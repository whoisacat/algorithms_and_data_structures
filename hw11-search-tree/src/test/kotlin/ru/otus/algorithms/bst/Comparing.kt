package ru.otus.algorithms.bst

import org.junit.jupiter.api.Assertions
import ru.otus.algorithms.bst.random.RandTree
import ru.otus.algorithms.bst.splay.SplayTree
import java.lang.Exception
import kotlin.random.Random

fun main() {

    var measurement1 = measureSplayRandom()
    var measurement2 = measureSplayOrdered()
    var measurement3 = measureRandRandom()
    var measurement4 = measureRandOrdered()
    println("type of tree|type of numbers| insert time\t| search time\t| delete time")
    println("splay\t\t| random\t\t|\t${measurement1[0]}\t\t\t|\t${measurement1[1]}\t\t\t|\t${measurement1[2]}")
    println("splay\t\t| ordered\t\t|\t${measurement2[0]}\t\t\t|\t${measurement2[2]}\t\t\t|\t${measurement2[2]}")
    println("random\t\t| random\t\t|\t${measurement3[0]}\t\t\t|\t${measurement3[1]}\t\t\t|\t${measurement3[2]}")
    println("random\t\t| ordered\t\t|\t${measurement4[0]}\t\t\t|\t${measurement4[1]}\t\t\t|\t${measurement4[2]}")
    println("Вывод")
    println("Если вставлять по порядку и искать, то лучший тут - splay за счет\nоптимизации вставки (не производятся повороты)." +
            " Если вставлять\nслучайные и искать, то лучший тут rnd tree. Медленное удаление\nsplay tree для случайных " +
            "элементов, но оно так замедляется из-за\nособенности реализации алгоритма - если не нашел элемент для\nудаления, " +
            "то выбрасывается исключение, эту особенность можно\nоптимизировать.")
}

private fun measureSplayOrdered(): Array<Long?> {
    val measurement = Array<Long?>(3) { null }
    val tree = SplayTree()

    var start = System.currentTimeMillis()
    for (i in 1..1000) {
        tree.insert(i)
    }
    measurement[0] = System.currentTimeMillis() - start

    start = System.currentTimeMillis()
    for (i in 201..300) {
        println("search ${tree.search(i)}")
    }
    measurement[1] = System.currentTimeMillis() - start
    val deleteSet = HashSet<Int>()
    while (deleteSet.size < 100) {
        deleteSet.add(Random.nextInt(9_999))
    }

    start = System.currentTimeMillis()

    for (i in 301..400)
        try {
            tree.remove(i)
        } catch (e: Exception) {
            Assertions.assertTrue(e.message!!.contains("has no key"))
            println(e.message)
        }
    measurement[2] = System.currentTimeMillis() - start
    return measurement
}

private fun measureSplayRandom(): Array<Long?> {
    val measurement = Array<Long?>(3) { null }
    val tree = SplayTree()
    val insertSet = HashSet<Int>()
    while (insertSet.size < 1000) {
        insertSet.add(Random.nextInt(9_999))
    }

    var start = System.currentTimeMillis()
    for (i in insertSet) {
        tree.insert(i)
    }
    measurement[0] = System.currentTimeMillis() - start

    val searchSet = HashSet<Int>()
    while (searchSet.size < 100) {
        searchSet.add(Random.nextInt(9_999))
    }

    start = System.currentTimeMillis()
    for (i in searchSet) {
        println("search ${tree.search(i)}")
    }
    measurement[1] = System.currentTimeMillis() - start
    val deleteSet = HashSet<Int>()
    while (deleteSet.size < 100) {
        deleteSet.add(Random.nextInt(9_999))
    }

    start = System.currentTimeMillis()
    for (i in deleteSet)
        try {
            tree.remove(i)
        } catch (e: Exception) {
            Assertions.assertTrue(e.message!!.contains("has no key"))
            println(e.message)
        }
    measurement[2] = System.currentTimeMillis() - start
    return measurement
}
private fun measureRandOrdered(): Array<Long?> {
    val measurement = Array<Long?>(3) { null }
    val tree = RandTree()

    var start = System.currentTimeMillis()
    for (i in 1..1000) {
        tree.insert(i)
    }
    measurement[0] = System.currentTimeMillis() - start

    start = System.currentTimeMillis()
    for (i in 201..300) {
        println("search ${tree.search(i)}")
    }
    measurement[1] = System.currentTimeMillis() - start
    val deleteSet = HashSet<Int>()
    while (deleteSet.size < 100) {
        deleteSet.add(Random.nextInt(9_999))
    }

    start = System.currentTimeMillis()

    for (i in 301..400)
        try {
            tree.remove(i)
        } catch (e: Exception) {
            Assertions.assertTrue(e.message!!.contains("has no key"))
            println(e.message)
        }
    measurement[2] = System.currentTimeMillis() - start
    return measurement
}

private fun measureRandRandom(): Array<Long?> {
    val measurement = Array<Long?>(3) { null }
    val tree = RandTree()
    val insertSet = HashSet<Int>()
    while (insertSet.size < 1000) {
        insertSet.add(Random.nextInt(9_999))
    }

    var start = System.currentTimeMillis()
    for (i in insertSet) {
        tree.insert(i)
    }
    measurement[0] = System.currentTimeMillis() - start

    val searchSet = HashSet<Int>()
    while (searchSet.size < 100) {
        searchSet.add(Random.nextInt(9_999))
    }

    start = System.currentTimeMillis()
    for (i in searchSet) {
        println("search ${tree.search(i)}")
    }
    measurement[1] = System.currentTimeMillis() - start
    val deleteSet = HashSet<Int>()
    while (deleteSet.size < 100) {
        deleteSet.add(Random.nextInt(9_999))
    }

    start = System.currentTimeMillis()
    for (i in deleteSet)
        try {
            tree.remove(i)
        } catch (e: Exception) {
            Assertions.assertTrue(e.message!!.contains("has no key"))
            println(e.message)
        }
    measurement[2] = System.currentTimeMillis() - start
    return measurement
}