package functions.exercise2.solution1

import kotlin.random.Random


open class Symbol(val identifier: Char)
object O : Symbol('O')
object X : Symbol('X')
object Empty : Symbol(' ')

data class Player(val name: String, val symbol: Symbol)

class TicTacToe(private val player1: Player, private val player2: Player, private val iOAdapter: IOAdapter) {

    private var currentPlayer = getRandomPlayer()
    private val playground = Playground()


    fun start() {
        iOAdapter.introduceGame()
        nextRound()
    }

    private fun nextRound() {
        when {
            playground.hasWinState() -> iOAdapter.announceWinner(currentPlayer, playground)
            playground.hasDrawState() -> iOAdapter.announceDraw(playground)
            else -> {
                toggleCurrentPlayer()
                playRound()
                nextRound()
            }
        }
    }

    private fun playRound() {
        iOAdapter.introduceRound(playground)
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
                iOAdapter.sayCellOutOfBounds()
                getValidPlayerInput()
            }
            !playground.isValidMove(choice) -> {
                iOAdapter.sayCellOccupied()
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
                iOAdapter.sayInvalidRowIndex()
                getPlayersChoice()
            }
            colIndex == null -> {
                iOAdapter.sayInvalidColumnIndex()
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
                iOAdapter.sayMissingRow()
                readIndicesString()
            }
            colIndexString.isNullOrEmpty() -> {
                iOAdapter.sayMissingColumn()
                readIndicesString()
            }
            else -> Pair(rowIndexString, colIndexString)
        }
    }

    private fun readPlayerInput(): String {
        return iOAdapter.askForChoice(currentPlayer, "zeile,spalte") ?: let {
            iOAdapter.sayNoInputFound()
            return readPlayerInput()
        }
    }

    private fun toggleCurrentPlayer() {
        currentPlayer = if (currentPlayer == player1) player2 else player1
    }


    private fun getRandomPlayer() = if (Random.nextBoolean()) player1 else player2


}