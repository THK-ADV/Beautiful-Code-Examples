package example7

/**
 * Use Searchable Names
 * @author Florian Herborn
 * @see 'MARTIN, Robert C. Clean code: example3.getA handbook of agile software craftsmanship. Pearson Education, 2009. S.23'
 **/
object Example7 {
    fun main() {
        val t = arrayOf<Int>()
        var s = 0
        (0 until 34).forEach { j ->
            s += (t[j] * 4) / 5
        }
    }
}