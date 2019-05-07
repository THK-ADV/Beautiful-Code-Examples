package example12

/**
 * Function Names
 * @author Florian Herborn
 **/
object Solution {

    class Rational (numerator: Int, denominator: Int) {
        companion object {
            fun fromInteger(number: Int) = Rational(number, 1)
        }
    }


    fun main(){
        val rational1 = Rational(1,2)
        val rational2 = Rational.fromInteger(1)
    }
}