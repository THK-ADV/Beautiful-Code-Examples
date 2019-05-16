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
            System.err.println(input.message)
            getPlayersChoice()
        }
    }

    private fun readPlayerInput(): PlayerInput {
        val indicesString = readLine()?.split(',') ?: return ErrorInput("Du hast entweder nichts eingegeben oder das Komma vergessen!")
        val rowIndexString = indicesString.getOrNull(0) ?: return ErrorInput("Du hast die Zeile vergessen!")
        val colIndexString = indicesString.getOrNull(1) ?: return ErrorInput("Du hast die Spalte vergessen!")
        val rowIndex = rowIndexString.toIntOrNull() ?: return ErrorInput("Dein Zeilenindex war keine Zahl!")
        val colIndex = colIndexString.toIntOrNull() ?: return ErrorInput("Dein Spaltenindex war keine Zahl!")
        if (!playground.isValidIndex(rowIndex)) return ErrorInput("Es gibt nur 3 Zeilen! Gebe einen index zwischen 0 und 2 ein!")
        if (!playground.isValidIndex(colIndex)) return ErrorInput("Es gibt nur 3 Spalten! Gebe einen index zwischen 0 und 2 ein!")
        if (!playground.isValidMove(rowIndex, colIndex)) return ErrorInput("Du kommst zu spät, hier war schon einer!")
        return IndicesInput(rowIndex, colIndex)
    }

    private fun toggleCurrentPlayer() {
        currentPlayer = if (currentPlayer == player1) player2 else player1
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