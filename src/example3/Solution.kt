package example3

/**
 * Avoid Disinformation
 * @author Florian Herborn
 * @see 'MARTIN, Robert C. Clean code: a handbook of agile software craftsmanship. Pearson Education, 2009. S.20'
 **/
object Solution {
    fun main() {

        var x = 0
        var y = 1
        var a = x

        if(y == 1) {
            a = y
        } else {
            a = 0
        }
    }
}