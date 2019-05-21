package desinformationen

import java.awt.Color

/**
 * @author Florian Herborn & Dennis Dubbert
 * @see 'MARTIN, Robert C. Clean code: A handbook of agile software craftsmanship. Pearson Education, 2009. S.20'
 **/

object LettersExample {

    val color = Color(255, 0, 0)
    val co1or = Color(0, 255, 0)
    val coIor = Color(0, 0, 255)

    var O = 1
    var l = 0

    fun changeState() {
        O = if (O == 0) 1 else 0
    }

    fun getMatchingAmount() : Int {
        var a = l

        if (l == 0)
            a = O + 1
        else
            a = O + l

        return a
    }

}
