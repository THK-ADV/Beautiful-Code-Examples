package example2

/**
 * Use Intention-Revealing Names
 * @author Florian Herborn
 * @see 'MARTIN, Robert C. Clean code: example3.getA handbook of agile software craftsmanship. Pearson Education, 2009. S.18'
 **/

object Solution {
    val gameBoard: ArrayList<Array<Int>> = ArrayList()

    const val FLAGGED = 4
    const val STATUS_VALUE = 0

    fun getFlaggedCells(): ArrayList<Array<Int>> {
        val flaggedCells = ArrayList<Array<Int>>()
        for (cell in gameBoard) {
            if (cell[STATUS_VALUE] == FLAGGED)
                flaggedCells.add(cell)
        }
        return flaggedCells
    }
}