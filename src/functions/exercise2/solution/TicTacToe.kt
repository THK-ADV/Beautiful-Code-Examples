package functions.exercise2.solution

import kotlin.random.Random

data class Player(val name: String, val symbol: Symbol)

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