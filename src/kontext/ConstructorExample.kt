package kontext

/**
 * @author Florian Herborn & Dennis Dubbert
 **/

object ConstructorExample {

    class Rational(val numerator: Int, val denominator: Int) {
        constructor(number: Int): this(number, 1)
    }

    fun main(){
        val rational1 = Rational(1,2)
        val rational2 = Rational(1)
    }

}