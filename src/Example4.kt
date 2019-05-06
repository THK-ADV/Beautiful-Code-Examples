/**
 * Make Meaningful Distinctions
 * @author Florian Herborn
 * @see 'MARTIN, Robert C. Clean code: a handbook of agile software craftsmanship. Pearson Education, 2009. S.21'
 **/

fun copyChars(a1: Array<Char>, a2: Array<Char>) {
    for (i in a1.indices) {
        a2[i] = a1[i]
    }
}