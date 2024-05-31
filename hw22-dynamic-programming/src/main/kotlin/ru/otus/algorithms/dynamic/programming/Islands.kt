package ru.otus.algorithms.dynamic.programming

fun numIslands(grid: Array<CharArray>): Int {
    if (grid.isEmpty()) return 0

    val numRows = grid.size
    val numCols = grid[0].size
    var count = 0

    for (i in 0 until numRows) {
        for (j in 0 until numCols) {
            if (grid[i][j] == '1') {
                count++
                dfs(grid, i, j)
            }
        }
    }

    return count
}

fun dfs(grid: Array<CharArray>, i: Int, j: Int) {
    if (i < 0 || i >= grid.size || j < 0 || j >= grid[0].size || grid[i][j] == '0') {
        return
    }

    grid[i][j] = '0'  // Помечаем текущую ячейку как посещенную

    // Рекурсивно посещаем все соседние ячейки (вверх, вниз, влево, вправо)
    dfs(grid, i + 1, j)
    dfs(grid, i - 1, j)
    dfs(grid, i, j + 1)
    dfs(grid, i, j - 1)
}

fun main() {
    val grid = arrayOf(
        charArrayOf('1', '1', '0', '1', '0'),
        charArrayOf('1', '0', '0', '0', '0'),
        charArrayOf('0', '1', '0', '1', '0'),
        charArrayOf('1', '0', '0', '1', '1')
    )

    println("Количество островов: ${numIslands(grid)}")
}
