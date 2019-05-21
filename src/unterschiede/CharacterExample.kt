package unterschiede

/**
 * @author Florian Herborn & Dennis Dubbert
 * @see 'MARTIN, Robert C. Clean code: A handbook of agile software craftsmanship. Pearson Education, 2009. S.21'
 **/

object CharacterExample {

    fun copyChars(a1: Array<Char>, a2: Array<Char>) {
        for (i in a1.indices) {
            a2[i] = a1[i]
        }
    }

}