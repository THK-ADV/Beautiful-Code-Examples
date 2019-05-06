/**
 * Use Intention-Revealing Names
 * @author Florian Herborn
 * @see 'MARTIN, Robert C. Clean code: a handbook of agile software craftsmanship. Pearson Education, 2009. S.18'
 **/

val gameBoard: ArrayList<Cell> = ArrayList()

const val FLAGGED = 0
const val STATUS_VALUE = 4

class Cell(val info: Array<Int>){
    fun isFlagged() = info[STATUS_VALUE] == FLAGGED
}

fun getFlaggedCells(): ArrayList<Cell> {
    val flaggedCells = ArrayList<Cell>()
    for (cell in gameBoard) {
        if (cell.isFlagged())
            flaggedCells.add(cell)
    }
    return flaggedCells
}