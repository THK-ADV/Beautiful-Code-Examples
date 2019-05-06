/**
 * Avoid Disinformation
 * @author Florian Herborn
 * @see 'MARTIN, Robert C. Clean code: a handbook of agile software craftsmanship. Pearson Education, 2009. S.20'
 **/

var x = 0
var y = 1
var a = x

fun main() {
    if(y == 1) {
        a = y
    } else {
        a = 0
    }
}