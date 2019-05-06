import java.util.*

/**
 * Make Meaningful Distinctions
 * @author Florian Herborn
 * @see 'MARTIN, Robert C. Clean code: a handbook of agile software craftsmanship. Pearson Education, 2009. S.23'
 **/

fun main() {
    val t = arrayOf<Int>()
    var s = 0
    (0 until 34).forEach { j ->
        s += (t[j] * 4) / 5
    }
}