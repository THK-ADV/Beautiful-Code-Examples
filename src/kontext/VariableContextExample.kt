package kontext

/**
 * @author Florian Herborn
 * @see 'MARTIN, Robert C. Clean code: A handbook of agile software craftsmanship. Pearson Education, 2009. S.28'
 **/

object VariableContextExample {

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