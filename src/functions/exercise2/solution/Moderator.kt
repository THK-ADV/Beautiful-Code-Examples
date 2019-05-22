package functions.exercise2.solution


class GermanTicTacToeConsoleModerator(private val name: String) : GameListener {
    override fun onGameStateChanged(gameState: GameState) {
        when (gameState) {
            is Started -> introduceGame()
            is RoundStarted -> introduceRound(gameState)
            is Win -> announceWinner(gameState)
            is Draw -> announceDraw(gameState)
        }
    }

    override fun onInputStateChanged(inputState: InputState) {
        when (inputState) {
            is NoInput -> sayNoInputFound()
            is InvalidRow -> sayInvalidRow()
            is InvalidColumn -> sayInvalidColumn()
            is MissingRow -> sayMissingRow()
            is MissingColumn -> sayMissingColumn()
            is CellOccupied -> sayCellOccupied()
            is CellOutOfBounds -> sayCellOutOfBounds()
            is Requesting -> askForInput()
        }
    }

    private fun askForInput() {
        say("Wähle eine Zelle. Deine Eingabe sollte so aussehen: <zeile>,<spalte>")
    }

    private fun introduceGame() {
        say("""
            Hallo, mein Name ist $name.
            Ich begleite euch bei diesem Tic Tac Toe Spiel und helfe euch weiter, wenn ihr etwas falsch macht.
        """.trimIndent())
    }

    private fun introduceRound(roundStartetState: RoundStarted) {
        say("Die nächste Runde beginnt. Du bist dran ${roundStartetState.player.name}. Dein zeichen ist ${roundStartetState.player.symbol}")
        showCurrentPlayground(roundStartetState.playground)
    }

    private fun showCurrentPlayground(playground: Playground) {
        say("Das Aktuelle Spielfeld sieht so aus:\n$playground")
    }

    private fun announceWinner(winState: Win) {
        say("${winState.winner.name} hat mit seinem Symbol ${winState.winner.symbol} gewonnen!")
        showCurrentPlayground(winState.playground)
    }

    private fun announceDraw(drawState: Draw) {
        say("Das Spiel ist unentschieden!")
        showCurrentPlayground(drawState.playground)
    }

    private fun sayNoInputFound() {
        say("Du hast entweder nichts eingegeben!")
    }

    private fun sayMissingRow() {
        say("Du hast die Zeile vergessen!")
    }

    private fun sayMissingColumn() {
        say("Du hast die Spalte vergessen!")
    }

    private fun sayInvalidRow() {
        say("Dein Zeilenindex war keine Zahl!")
    }

    private fun sayInvalidColumn() {
        say("Dein Spaltenindex war keine Zahl!")
    }

    private fun sayCellOutOfBounds() {
        say("Es gibt nur 3 Spalten! Gebe einen index zwischen 0 und 2 ein!")
    }

    private fun sayCellOccupied() {
        say("Du kommst zu spät, hier war schon einer!")
    }

    private fun say(message: String) {
        println("$name:\n$message\n")
    }

}