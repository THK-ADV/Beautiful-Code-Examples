package functions.exercise2.solution

object Game {
    @JvmStatic
    fun main(args: Array<String>) {
        val moderator = GermanTicTacToeConsoleModerator("Peter")
        val player1 = Player("Paul", X)
        val player2 = Player("Hans", O)
        val playerSupplier = RoundRobinPlayerSupplier(player1, player2)
        val game = TicTacToe(playerSupplier)
        game.addObserver(moderator)
        game.start()
    }
}