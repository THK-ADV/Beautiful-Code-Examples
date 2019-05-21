package suchbar

/**
 * @author Florian Herborn & Dennis Dubbert
 * @see 'MARTIN, Robert C. Clean code: A handbook of agile software craftsmanship. Pearson Education, 2009. S.23'
 **/

object TasksExample {

    val t = arrayOf<Int>()

    fun getWeeks() : Int {
        var s = 0

        (0 until 34).forEach { i ->
            s += (t[i] * 4) / 5
        }

        return s
    }

}