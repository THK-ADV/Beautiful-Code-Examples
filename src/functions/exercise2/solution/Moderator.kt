package functions.exercise2.solution

interface Moderator {
    val io: IOAdapter
    val name: String
    fun introduceGame()
    fun introduceRound(playground: Playground)
    fun showPlayground(playground: Playground)
    fun announceWinner(player: Player, playground: Playground)
    fun announceDraw(playground: Playground)
    fun sayNoInputFound()
    fun sayMissingRow()
    fun sayMissingColumn()
    fun sayInvalidRowIndex()
    fun sayInvalidColumnIndex()
    fun sayCellOutOfBounds()
    fun sayCellOccupied()
    fun askForChoice(player: Player, inputPattern: String): String?
}

class GermanTicTacToeModerator(override val name: String, override val io: IOAdapter): Moderator {
    override fun introduceRound(playground: Playground) {
        io printMessage "Die nächste Runde beginnt. Das Aktuelle Spielfeld sieht so aus:"
        showPlayground(playground)
    }

    override fun announceWinner(player: Player, playground: Playground) {
        io printMessage "${player.name} hat mit seinem Symbol ${player.symbol} gewonnen!"
        showPlayground(playground)
    }

    override fun announceDraw(playground: Playground) {
        io printMessage "Das Spiel ist unentschieden!"
        showPlayground(playground)
    }

    override fun showPlayground(playground: Playground) {
        io printMessage playground.toString()
    }

    override fun introduceGame() {
        io printMessage """
            Hallo, mein Name ist $name.
            Ich begleite euch bei diesem Tic Tac Toe Spiel und helfe euch weiter, wenn ihr etwas falsch macht.
        """.trimIndent()
    }

    override fun sayNoInputFound() {
        io printError "Du hast entweder nichts eingegeben!"
    }

    override fun sayMissingRow() {
        io printError "Du hast die Zeile vergessen!"
    }

    override fun sayMissingColumn() {
        io printError "Du hast die Spalte vergessen!"
    }

    override fun sayInvalidRowIndex() {
        io printError "Dein Zeilenindex war keine Zahl!"
    }

    override fun sayInvalidColumnIndex() {
        io printError "Dein Spaltenindex war keine Zahl!"
    }

    override fun sayCellOutOfBounds() {
        io printError "Es gibt nur 3 Spalten! Gebe einen index zwischen 0 und 2 ein!"
    }

    override fun sayCellOccupied() {
        io printError "Du kommst zu spät, hier war schon einer!"
    }

    override fun askForChoice(player: Player, inputPattern: String): String? {
        io printMessage "Du bist dran ${player.name}. Mach deine eingabe in der Form $inputPattern. Dein zeichen ist ${player.symbol}"
        return io.readInput()
    }

}