package kontext

/**
 * @author Florian Herborn
 * @see 'MARTIN, Robert C. Clean code: A handbook of agile software craftsmanship. Pearson Education, 2009. S.29'
 **/

object VariableContextSolution {

    class GuessStatisticsMessage {
        lateinit var number: String
        lateinit var verb: String
        lateinit var pluralModifier: String

        fun make(candidate: Char, count: Int): String {
            createPluralDependentMessageParts(count)
            return "There $verb $number $candidate$pluralModifier"
        }

        private fun createPluralDependentMessageParts(count: Int) {
            when (count) {
                0 -> thereAreNoLetters()
                1 -> thereIsOneLetter()
                else -> thereAreAnyLetters(count)
            }
        }

        private fun thereAreAnyLetters(count: Int) {
            number = count.toString()
            verb = "are"
            pluralModifier = "s"
        }

        private fun thereIsOneLetter() {
            number = "1"
            verb = "is"
            pluralModifier = ""
        }

        private fun thereAreNoLetters() {
            number = "no"
            verb = "are"
            pluralModifier = "s"
        }
    }
}