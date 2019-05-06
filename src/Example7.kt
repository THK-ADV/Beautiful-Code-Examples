import java.util.*

/**
 * Make Meaningful Distinctions
 * @author Florian Herborn
 * @see 'MARTIN, Robert C. Clean code: a handbook of agile software craftsmanship. Pearson Education, 2009. S.23'
 **/

fun main() {
    val taskEstimate = arrayOf<Int>()

    val realDaysPerIdealDay = 4
    val workDaysPerWeak = 5
    val numberOfTasks = 34

    var sum = 0
    (0 until numberOfTasks).forEach { j ->
        val realTaskDays = taskEstimate[j] * realDaysPerIdealDay
        val realTaskWeeks = realTaskDays / workDaysPerWeak
        sum += realTaskWeeks
    }
}