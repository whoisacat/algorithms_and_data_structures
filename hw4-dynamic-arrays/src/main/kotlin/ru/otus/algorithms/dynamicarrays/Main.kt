package ru.otus.algorithms.dynamicarrays

import com.whoisacat.edu.otus.algoriths.dynamicarrays.*
import kotlin.random.Random

fun main(args: Array<String>) {

    //Сравнить время выполнения разных операций для разных массивов с разным порядком значений.
    //
    //Сделать обёртку над ArrayList() и тоже сравнить. Составить таблицу и приложить её скриншот.
    // Сделать выводы и сформулировать их в нескольких предложениях.

    val addResults: Array<Long> = Array(5) { 0 }
    val removeResults: Array<Long> = Array(5) { 0 }

    println("Добавление/удаление в конец массива")
    println("действие|кол-во| SingleArray| VectorArray| FactorArray| MatrixArray  | BoxedArrayList")
    for (q in 1..5) {
        val number = Math.pow(10.0, q.toDouble()).toInt()
        for (i in addResults.indices) {
            val arrays: Array<DynamicArray<Int>?> = Array(5) {null}
            arrays[0] = SingleArray()
            arrays[1] = VectorArray(32)
            arrays[2] = FactorArray(2)
            arrays[3] = MatrixArray()
            arrays[4] = BoxedArrayList()
            val startTime = System.nanoTime()
            add(number, arrays[i]!!)
            addResults[i] = System.nanoTime() - startTime
            val startRemoveTime = System.nanoTime()
            remove(number, arrays[i]!!)
            removeResults[i] = System.nanoTime() - startRemoveTime
        }
        println("add\t\t|${"%6d".format(number)}|${"%12d".format(addResults[0])}|${"%12d".format(addResults[1])}|${"%12d".format(addResults[2])}|${"%14d".format(addResults[3])}|${"%12d".format(addResults[4])}")
        println("rem\t\t|${"%6d".format(number)}|${"%12d".format(removeResults[0])}|${"%12d".format(removeResults[1])}|${"%12d".format(removeResults[2])}|${"%14d".format(removeResults[3])}|${"%12d".format(removeResults[4])}")
    }

    println("Добавление/удаление в середину массива")
    println("действие|кол-во| SingleArray| VectorArray| FactorArray| MatrixArray  | BoxedArrayList")
    for (q in 1..5) {
        val number = Math.pow(10.0, q.toDouble()).toInt()
        for (i in addResults.indices) {
            val arrays: Array<DynamicArray<Int>?> = Array(5) {null}
            arrays[0] = SingleArray()
            arrays[1] = VectorArray(32)
            arrays[2] = FactorArray(2)
            arrays[3] = MatrixArray()
            arrays[4] = BoxedArrayList()
            val startTime = System.nanoTime()
            addInTheMiddle(number, arrays[i]!!)
            addResults[i] = System.nanoTime() - startTime
            val startRemoveTime = System.nanoTime()
            removeFromTheMiddle(number, arrays[i]!!)
            removeResults[i] = System.nanoTime() - startRemoveTime
        }
        println("add\t\t|${"%6d".format(number)}|${"%12d".format(addResults[0])}|${"%12d".format(addResults[1])}|${"%12d".format(addResults[2])}|${"%14d".format(addResults[3])}|${"%12d".format(addResults[4])}")
        println("rem\t\t|${"%6d".format(number)}|${"%12d".format(removeResults[0])}|${"%12d".format(removeResults[1])}|${"%12d".format(removeResults[2])}|${"%14d".format(removeResults[3])}|${"%12d".format(removeResults[4])}")
    }
    println("При добавлении в конец динамического массива сложность для всех массивов O(N), но коэффецинты разные." +
            " Для ${SingleArray::class.java.simpleName} коэффецинт самый большой, для ${FactorArray::class.java.simpleName} " +
            "(и обертки над ${ArrayList::class.java.simpleName}) самый мальенткий, время выполнения растет пропорционально " +
            "росту количества добавляемых элементов с попправкой на нагрузку на память. Для ${MatrixArray::class.java.simpleName} " +
            "нагрузка на память из-за копирования не повышается, поэтому у него не происходит лавинного повышения времени выполнения.\n" +
            "При удалении из конца происходит примерно то же.\n" +
            "Добавление в середину работает примерно так же, но преимущество для ${MatrixArray::class.java.simpleName}" +
            " пропадает из-за постоянных перестановок и на больших чмслах вставки и особеноо удаления этот тип динамического массива " +
            "работает значительно медленнее")

}

fun add(number: Int, dynamicArray: DynamicArray<Int>) {
    for (i in 0..number) {
        dynamicArray.add(Random.nextInt(), i)
    }
}

fun remove(number: Int, dynamicArray: DynamicArray<Int>) {
    for (i in number downTo 0) {
        dynamicArray.remove(i)
    }
}

fun addInTheMiddle(number: Int, dynamicArray: DynamicArray<Int>) {
    for (i in 0..number) {
        dynamicArray.add(Random.nextInt(), i / 2)
    }
}

fun removeFromTheMiddle(number: Int, dynamicArray: DynamicArray<Int>) {
    for (i in number downTo 0) {
        dynamicArray.remove(i / 2)
    }
}
