package functions.exercise2.solution1

interface IOAdapter {
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

class GermanTicTacToeIOAdapter(override val name: String): IOAdapter {
    override fun introduceRound(playground: Playground) {
        println("Die nächste Runde beginnt. Das Aktuelle Spielfeld sieht so aus:")
        showPlayground(playground)
    }

    override fun announceWinner(player: Player, playground: Playground) {
        println("${player.name} hat mit seinem Symbol ${player.symbol} gewonnen!")
        showPlayground(playground)
    }

    override fun announceDraw(playground: Playground) {
        println("Das Spiel ist unentschieden!")
        showPlayground(playground)
    }

    override fun showPlayground(playground: Playground) {
        println(playground.toString())
    }

    override fun introduceGame() {
        println("""
            Hallo, mein Name ist $name.
            Ich begleite euch bei diesem Tic Tac Toe Spiel und helfe euch weiter, wenn ihr etwas falsch macht.
        """.trimIndent())
    }

    override fun sayNoInputFound() {
        println("Du hast entweder nichts eingegeben!")
    }

    override fun sayMissingRow() {
        println("Du hast die Zeile vergessen!")
    }

    override fun sayMissingColumn() {
        println("Du hast die Spalte vergessen!")
    }

    override fun sayInvalidRowIndex() {
        println("Dein Zeilenindex war keine Zahl!")
    }

    override fun sayInvalidColumnIndex() {
        println("Dein Spaltenindex war keine Zahl!")
    }

    override fun sayCellOutOfBounds() {
        println("Es gibt nur 3 Spalten! Gebe einen index zwischen 0 und 2 ein!")
    }

    override fun sayCellOccupied() {
        println("Du kommst zu spät, hier war schon einer!")
    }

    override fun askForChoice(player: Player, inputPattern: String): String? {
        println("Du bist dran ${player.name}. Mach deine eingabe in der Form $inputPattern. Dein zeichen ist ${player.symbol}")
        return readLine()
    }

}