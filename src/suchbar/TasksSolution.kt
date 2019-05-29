package suchbar

/**
 * @author Florian Herborn & Dennis Dubbert
 * @see 'MARTIN, Robert C. Clean code: a handbook of agile software craftsmanship. Pearson Education, 2009. S.23'
 **/

object TasksSolution {
    val taskEstimate : Array<Int> = TODO()

    val numberOfTasks = 34
    val realDaysPerIdealDay = 4
    val workDaysPerWeak = 5

    fun getNeededWeeksForTasks() : Int {
        var neededWeeks = 0

        (0 until numberOfTasks).forEach { i ->
            val realTaskDays = taskEstimate[i] * realDaysPerIdealDay
            val realTaskWeeks = realTaskDays / workDaysPerWeak
            neededWeeks += realTaskWeeks
        }

        return neededWeeks
    }
}