package functions.exercise2.solution
sealed class Symbol(private val identifier: Char) {
    override fun toString() = identifier.toString()
}

object O : Symbol('O')
object X : Symbol('X')
object Empty : Symbol(' ')


data class Player(val name: String, val symbol: Symbol)