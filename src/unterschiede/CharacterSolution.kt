package unterschiede

/**
 * @author Florian Herborn & Dennis Dubbert
 * @see 'MARTIN, Robert C. Clean code: a handbook of agile software craftsmanship. Pearson Education, 2009. S.21'
 **/

object CharacterSolution {

    fun copyChars(source: Array<Char>, destination: Array<Char>) {
        for (i in source.indices) {
            destination[i] = source[i]
        }
    }

}