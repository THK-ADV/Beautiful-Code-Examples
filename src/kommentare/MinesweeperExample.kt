package kommentare

/**
 * @author Florian Herborn & Dennis Dubbert
 * @see 'MARTIN, Robert C. Clean code: A handbook of agile software craftsmanship. Pearson Education, 2009. S.18'
 **/

object MinesweeperExample {

    class Minesweeper {
        // the gameboard as an array. Contains all the cells, which are Integer arrays.
        val theList = ArrayList<Array<Int>>()

        // returns all the flagged cells
        fun getThem(): ArrayList<Array<Int>> {
            // array containing found flagged cells
            val list1 = ArrayList<Array<Int>>()

            for (x in theList) {
                // index 0 contains the status value. If it equals 4, the cell is flagged
                if (x[0] == 4)
                    list1.add(x)
            }

            return list1
        }
    }

}