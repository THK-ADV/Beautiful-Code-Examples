package functions.exercise2.solution

import kotlin.random.Random



sealed class Symbol(private val identifier: Char) {
    override fun toString() = identifier.toString()
}

object O : Symbol('O')
object X : Symbol('X')
object Empty : Symbol(' ')

data class Player(val name: String, val symbol: Symbol)

private sealed class PlayerInput
private class IndicesInput(val rowIndex: Int, val colIndex: Int) : PlayerInput()
private class ErrorInput(val message: String) : PlayerInput()

private class Playground {

    private val map = Array(CELLS_PER_ROW) { Array<Symbol>(CELLS_PER_COLUMN) { Empty } }

    fun hasWinState(): Boolean {
        return hasFullRow()
                || hasFullColumn()
                || hasFullDiag()
    }

    private fun hasFullDiag() =
            allTheSameAndNotEmpty(map[0][0], map[1][1], map[2][2])
                    || allTheSameAndNotEmpty(map[0][2], map[1][1], map[2][0])

    private fun hasFullRow() =
            isFullRow(0)
                    || isFullRow(1)
                    || isFullRow(2)

    private fun hasFullColumn() =
            isFullColumn(0)
                    || isFullColumn(1)
                    || isFullColumn(2)

    private fun isFullRow(rowIndex: Int) = allTheSameAndNotEmpty(map[rowIndex][0], map[rowIndex][1], map[rowIndex][2])

    private fun isFullColumn(colIndex: Int) = allTheSameAndNotEmpty(map[0][colIndex], map[1][colIndex], map[2][colIndex])

    private fun allTheSameAndNotEmpty(vararg symbols: Symbol) = symbols.all {
        it !is Empty && it == symbols.firstOrNull()
    }

    fun hasDrawState() = map.flatten().none { it is Empty }

    operator fun set(rowIndex: Int, colIndex: Int, symbol: Symbol) {
        map[rowIndex][colIndex] = symbol
    }

    operator fun get(rowIndex: Int): Array<Symbol> = map[rowIndex]

    operator fun get(rowIndex: Int, colIndex: Int): Symbol = map[rowIndex][colIndex]

    override fun toString(): String {
        return """
                    0   1   2
                0  _${map[0][0]}_|_${map[0][1]}_|_${map[0][2]}_
                1  _${map[1][0]}_|_${map[1][1]}_|_${map[1][2]}_
                2   ${map[2][0]} | ${map[2][1]} | ${map[2][2]}
            """.trimIndent()
    }

    companion object {
        private const val CELLS_PER_ROW = 3
        private const val CELLS_PER_COLUMN = 3
    }

}


class TicTacToe(private val player1: Player, private val player2: Player) {

    private var currentPlayer = getRandomPlayer()
    private val playground = Playground()

    fun start() {
        nextRound()
    }

    private fun nextRound() {
        when {
            playground.hasWinState() -> printWinnersInfo()
            playground.hasDrawState() -> printDrawInfo()
            else -> {
                toggleCurrentPlayer()
                playRound()
                nextRound()
            }
        }
    }

    private fun playRound() {
        printRoundInfo()
        processPlayerInput()
    }

    private fun processPlayerInput() {
        val choice = getPlayersChoice()
        playground[choice.rowIndex][choice.colIndex] = currentPlayer.symbol
    }

    private fun getPlayersChoice(): IndicesInput = when (val input = readPlayerInput()) {
        is IndicesInput -> input
        is ErrorInput -> {
            printErrorMessage(input)
            getPlayersChoice()
        }

    }

    private fun readPlayerInput(): PlayerInput {
        val indicesString = readLine()?.split(',') ?: return ErrorInput("Du musst eine Eingabe tätigen")
        val rowIndexString = indicesString.getOrNull(0) ?: return ErrorInput("Du musst einen row index angeben")
        val colIndexString = indicesString.getOrNull(1) ?: return ErrorInput("Du musst einen column index angeben")
        val rowIndex = rowIndexString.toIntOrNull() ?: return ErrorInput("Du hast einen invaliden row index angegeben")
        val colIndex = colIndexString.toIntOrNull() ?: return ErrorInput("Du hast einen invaliden column index angegeben")
        return IndicesInput(rowIndex, colIndex)
    }

    private fun toggleCurrentPlayer() {
        currentPlayer = if (currentPlayer == player1) player2 else player1
    }

    private fun printErrorMessage(input: ErrorInput) {
        error(input.message)
    }

    private fun printRoundInfo() {
        printCurrentPlayerInfo()
        printInputHint()
        printPlayground()
    }

    private fun printDrawInfo() {
        println("Das Spiel ist unentschieden")
        printPlayground()
    }

    private fun printWinnersInfo() {
        println("${currentPlayer.name} hat mit seinem Symbol ${currentPlayer.symbol} gewonnen!")
        printPlayground()
    }

    private fun printPlayground() {
        println(playground)
    }

    private fun printCurrentPlayerInfo() {
        println("${currentPlayer.name}, du bist an der Reihe. Dein Symbol ist ${currentPlayer.symbol}")
    }

    private fun printInputHint() {
        println("Bitte gebe ein, in welche Zelle dein Symbol gesetzt werden soll. Gebe deine Auswahl in folgender Form ein: rowIndex,colIndex")
    }


    private fun getRandomPlayer() = if (Random.nextBoolean()) player1 else player2


}

object Game {
    @JvmStatic
    fun main(args: Array<String>) {
        val game = TicTacToe(Player("Paul", O), Player("Hans", X))
        game.start()
    }
}