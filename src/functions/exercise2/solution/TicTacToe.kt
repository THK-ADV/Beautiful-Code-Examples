package functions.exercise2.solution

import kotlin.random.Random

data class Player(val name: String, val symbol: Symbol)

private sealed class PlayerInput
private class IndicesInput(val rowIndex: Int, val colIndex: Int) : PlayerInput()
private class ErrorInput(val message: String) : PlayerInput()

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