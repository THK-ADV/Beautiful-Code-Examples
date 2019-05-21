package kontext

/**
 * @author Florian Herborn & Dennis Dubbert
 **/

object ConstructorSolution {

    // Idiomatischer Weg in Kotlin sind default-Parameter
    class Rational (val numerator: Int, val denominator: Int) {
        companion object {
            fun fromInteger(number: Int) = Rational(number, 1)
        }
    }


    fun main(){
        val rational1 = Rational(1,2)
        val rational2 = Rational.fromInteger(1)
    }
}