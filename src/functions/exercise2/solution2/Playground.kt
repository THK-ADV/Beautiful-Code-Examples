package functions.exercise2.solution2

data class Cell(val rowIndex: Int, val colIndex: Int)

class Playground {

    private val map = Array(CELLS_PER_ROW) { Array<Symbol>(CELLS_PER_COLUMN) { Empty } }

    fun hasWinState(): Boolean {
        return hasFullRow()
                || hasFullColumn()
                || hasFullDiag()
    }

    private fun hasFullDiag() =
            allTheSameAndNotEmpty(map[0][0], map[1][1], map[2][2])
                    || allTheSameAndNotEmpty(map[0][2], map[1][1], map[2][0])

    private fun hasFullRow() =
            isFullRow(0)
                    || isFullRow(1)
                    || isFullRow(2)

    private fun hasFullColumn() =
            isFullColumn(0)
                    || isFullColumn(1)
                    || isFullColumn(2)

    fun isValidMove(cell: Cell) = isValidMove(cell.rowIndex, cell.colIndex)
    fun isValidMove(rowIndex: Int, colIndex: Int) = this[rowIndex, colIndex] is Empty

    private fun isValidIndex(index: Int) = index in (0..2)

    fun isOnBoard(cell: Cell) = isOnBoard(cell.rowIndex, cell.colIndex)
    fun isOnBoard(rowIndex: Int, colIndex: Int) = isValidIndex(rowIndex) && isValidIndex(colIndex)

    private fun isFullRow(rowIndex: Int) = allTheSameAndNotEmpty(map[rowIndex][0], map[rowIndex][1], map[rowIndex][2])

    private fun isFullColumn(colIndex: Int) = allTheSameAndNotEmpty(map[0][colIndex], map[1][colIndex], map[2][colIndex])

    private fun allTheSameAndNotEmpty(vararg symbols: Symbol) = symbols.all {
        it !is Empty && it == symbols.firstOrNull()
    }

    fun hasDrawState() = map.flatten().none { it is Empty }

    operator fun set(cell: Cell, symbol: Symbol) {
        map[cell.rowIndex][cell.colIndex] = symbol
    }

    operator fun get(cell: Cell): Symbol = map[cell.rowIndex][cell.colIndex]
    operator fun get(rowIndex: Int, colIndex: Int): Symbol = map[rowIndex][colIndex]

    override fun toString(): String {
        return """
                    0   1   2
                0  _${map[0][0]}_|_${map[0][1]}_|_${map[0][2]}_
                1  _${map[1][0]}_|_${map[1][1]}_|_${map[1][2]}_
                2   ${map[2][0]} | ${map[2][1]} | ${map[2][2]}
            """.trimIndent()
    }


    companion object {
        private const val CELLS_PER_ROW = 3
        private const val CELLS_PER_COLUMN = 3
    }

}