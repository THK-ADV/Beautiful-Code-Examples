package functions.exercise2.solution

object Game {
    @JvmStatic
    fun main(args: Array<String>) {
        val game = TicTacToe(Player("Paul", O), Player("Hans", X))
        game.start()
    }
}