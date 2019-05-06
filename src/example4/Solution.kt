package example4

/**
 * Make Meaningful Distinctions
 * @author Florian Herborn
 * @see 'MARTIN, Robert C. Clean code: a handbook of agile software craftsmanship. Pearson Education, 2009. S.21'
 **/
object Solution {
    fun copyChars(source: Array<Char>, destination: Array<Char>) {
        for (i in source.indices) {
            destination[i] = source[i]
        }
    }
}