package example12

/**
 * Method Names
 * @author Florian Herborn
 **/
object Example12 {

    class Rational(numerator: Int, denominator: Int) {
        constructor(number: Int): this(number, 1)
    }

    fun main(){
        val rational1 = Rational(1,2)
        val rational2 = Rational(1)
    }

}