package functions.exercise2.solution

import kotlin.random.Random

data class Player(val name: String, val symbol: Symbol)

data class Cell(val rowIndex: Int, val colIndex: Int)

interface IOAdapter {
    infix fun printMessage(message: String)
    infix fun printError(message: String)
    fun readInput(): String?
}

class ConsoleIOAdapter: IOAdapter {
    override fun readInput() = readLine()
    override fun printMessage(message: String) {
        println(message)
    }
    override fun printError(message: String) {
        System.err.println(message)
    }
}

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
    fun sayRowOutOfBounds()
    fun sayCellOutOfBounds()
    fun sayCellOccupied()
    fun sayInvalidInput(input: String, expected: String)
    fun sayChoice(rowIndex: Int, colIndex: Int)
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

    override fun sayChoice(rowIndex: Int, colIndex: Int) {
        io printMessage "Deine Wahl war Zeile $rowIndex und Spalte $colIndex"
    }

    override fun sayInvalidInput(input: String, expected: String) {
        io printError "Du hast $input einegeben, das entspricht jedoch nicht der Form, in der du deine Eingabe machen sollst. Versuche es erneut in der Form: $expected"
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

    override fun sayRowOutOfBounds() {
        io printError "Es gibt nur 3 Zeilen! Gebe einen index zwischen 0 und 2 ein!"
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

class TicTacToe(private val player1: Player, private val player2: Player, private val moderator: Moderator) {

    private var currentPlayer = getRandomPlayer()
    private val playground = Playground()


    fun start() {
        moderator.introduceGame()
        nextRound()
    }

    private fun nextRound() {
        when {
            playground.hasWinState() -> moderator.announceWinner(currentPlayer, playground)
            playground.hasDrawState() -> moderator.announceDraw(playground)
            else -> {
                toggleCurrentPlayer()
                playRound()
                nextRound()
            }
        }
    }

    private fun playRound() {
        moderator.introduceRound(playground)
        processPlayerInput()
    }

    private fun processPlayerInput() {
        val choice = getValidPlayerInput()
        playground[choice] = currentPlayer.symbol
    }


    private fun getValidPlayerInput(): Cell {
        val choice = getPlayersChoice()
        return when {
            !playground.isCellOnBoard(choice) -> {
                moderator.sayCellOutOfBounds()
                getValidPlayerInput()
            }
            !playground.isValidMove(choice) -> {
                moderator.sayCellOccupied()
                getValidPlayerInput()
            }
            else -> choice
        }
    }

    private fun getPlayersChoice(): Cell {
        val (rowIndexString, colIndexString) = readIndicesString()
        val rowIndex = rowIndexString.toIntOrNull()
        val colIndex = colIndexString.toIntOrNull()
        return when {
            rowIndex == null -> {
                moderator.sayInvalidRowIndex()
                getPlayersChoice()
            }
            colIndex == null -> {
                moderator.sayInvalidColumnIndex()
                getPlayersChoice()
            }
            else -> Cell(rowIndex, colIndex)
        }
    }

    private fun readIndicesString(): Pair<String, String> {
        val input = readPlayerInput()
        val indicesString = input.split(',')
        val rowIndexString = indicesString.getOrNull(0)
        val colIndexString = indicesString.getOrNull(1)
        return when {
            rowIndexString.isNullOrEmpty() -> {
                moderator.sayMissingRow()
                readIndicesString()
            }
            colIndexString.isNullOrEmpty() -> {
                moderator.sayMissingColumn()
                readIndicesString()
            }
            else -> Pair(rowIndexString, colIndexString)
        }
    }

    private fun readPlayerInput(): String {
        return moderator.askForChoice(currentPlayer, "zeile,spalte") ?: let {
            moderator.sayNoInputFound()
            return readPlayerInput()
        }
    }

    private fun toggleCurrentPlayer() {
        currentPlayer = if (currentPlayer == player1) player2 else player1
    }


    private fun getRandomPlayer() = if (Random.nextBoolean()) player1 else player2


}