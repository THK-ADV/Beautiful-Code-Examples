package functions.exercise2.solution

import kotlin.random.Random




object Game {
    @JvmStatic
    fun main(args: Array<String>) {
        val game = TicTacToe(Player("Paul", O), Player("Hans", X))
        game.start()
    }
}