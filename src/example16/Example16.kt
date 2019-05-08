package example16

/**
 * Variables with unclear Context
 * @author Florian Herborn
 * @see 'MARTIN, Robert C. Clean code: example3.getA handbook of agile software craftsmanship. Pearson Education, 2009. S.28'
 **/
object Example16 {

    fun printGuessStatistics(candidate: Char, count: Int) {
        val number: String
        val verb: String
        var pluralModifier  =  ""

        when (count) {
            0 -> {
                number = "no"
                verb = "are"
            }
            1 -> {
                number = "1"
                verb = "is"
            }
            else -> {
                number = count.toString()
                verb = "are"
                pluralModifier = "s"
            }
        }

        val guessMessage = "There $verb $number $candidate$pluralModifier"
        print(guessMessage)
    }

}