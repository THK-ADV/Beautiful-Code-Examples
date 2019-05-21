package kommentare

object PrimeGeneratorExample {

    /**
     * @author Dennis Dubbert
     * @see 'MARTIN, Robert C. Clean code: A handbook of agile software craftsmanship. Pearson Education, 2009. S.71 - 72'
     */

    /**
     * This class generates prime numbers up to a user specified maximum. The algorithm used is the Sieve of Eratosthenes.
     * <p>
     * Eratosthenes of Cyrene, b. c. 276 BC, Cyrene, Libya -- d. c. 194, Alexandria.
     * The first man to calculate the circumference of the Earth. Also known for working on calendars with leap years and
     * ran the library at Alexandria.
     * <p>
     * The algorithm is quite simple. Given an array of integers starting at 2: Cross out all multiples of 2. Find the next
     * uncrossed integer, and cross out all of its multiples. Repeat until you have passed the square root of the maximum
     * value.
     *
     * @author Alphonse
     * @version 13 Feb 2002 atp
     **/

    class PrimeGenerator {
        companion object {
            // List of booleans showing if a value has been crossed out
            private var crossedOut = BooleanArray(0)

            /**
             * Generates primes up to a maximum value
             * @param maxValue the maximum value
             * @return an array, containing all found primes
             */
            fun generatePrimes(maxValue : Int) : IntArray {
                return when {
                    maxValue < 2 -> IntArray(0)
                    else -> {
                        uncrossIntegersUpTo(maxValue)
                        crossOutMultiples()
                        getUncrossedIntegers()
                    }
                }
            }

            private fun uncrossIntegersUpTo(maxValue : Int) {
                crossedOut = BooleanArray(maxValue + 1) { it -> it < 2 }
            }

            private fun crossOutMultiples() {
                val limit = determineIterationLimit()

                for (i in 2..limit) {
                    if (notCrossed(i)) crossOutMultiplesOf(i)
                }
            }

            private fun determineIterationLimit() : Int {
                // Every multiple in the array has a prime factor that is less than or equal to the root of the array size,
                // so we don't have to cross out multiples of numbers larger than that root.
                val iterationLimit = Math.sqrt(crossedOut.size.toDouble())
                return iterationLimit.toInt()
            }

            private fun crossOutMultiplesOf(i : Int) {
                for (index in (2 * i) until crossedOut.size step i) {
                    crossedOut[index] = true
                }
            }

            private fun notCrossed(i : Int) = crossedOut[i] == false

            private fun getUncrossedIntegers() : IntArray {
                val result = IntArray(numberOfUncrossedIntegers())

                var j = 0
                for (i in 2 until crossedOut.size) {
                    if (notCrossed(i)) {
                        result[j] = i
                        j += 1
                    }
                }

                return result
            }

            private fun numberOfUncrossedIntegers() = crossedOut.count { it == false }
        }
    }

}
