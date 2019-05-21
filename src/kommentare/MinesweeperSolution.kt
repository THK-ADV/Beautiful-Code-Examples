package kommentare

/**
 * @author Florian Herborn & Dennis Dubbert
 * @see 'MARTIN, Robert C. Clean code: A handbook of agile software craftsmanship. Pearson Education, 2009. S.18'
 **/

object MinesweeperSolution {

    class Minesweeper {
        companion object {
            const val FLAGGED = 4
            const val STATUS_VALUE = 0
        }

        val gameBoard: ArrayList<Array<Int>> = ArrayList()

        fun getFlaggedCells(): ArrayList<Array<Int>> {
            val flaggedCells = ArrayList<Array<Int>>()

            for (cell in gameBoard) {
                if (cell[STATUS_VALUE] == FLAGGED)
                    flaggedCells.add(cell)
            }

            return flaggedCells
        }
    }

}