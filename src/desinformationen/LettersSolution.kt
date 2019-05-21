package desinformationen

import java.awt.Color

/**
 * @author Florian Herborn & Dennis Dubbert
 * @see 'MARTIN, Robert C. Clean code: a handbook of agile software craftsmanship. Pearson Education, 2009. S.20'
 **/

object LettersSolution {

    val red = Color(255, 0, 0)
    val blue = Color(0, 255, 0)
    val green = Color(0, 0, 255)

    var state = 1
    var amount = 0

    fun changeState() {
        state = if (state == 0) 1 else 0
    }

    fun getMatchingAmount() : Int {
        var a = amount

        if (amount == 0)
            a = state + 1
        else
            a = state + amount

        return a
    }

}