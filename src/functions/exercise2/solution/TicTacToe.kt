package functions.exercise2.solution


interface GameListener {
    fun onGameStateChanged(gameState: GameState)
    fun onInputStateChanged(inputState: InputState)
    fun onInputRequested(): String?
}

private class DefaultGameListener : GameListener {
    override fun onGameStateChanged(gameState: GameState) {}
    override fun onInputStateChanged(inputState: InputState) {}
    override fun onInputRequested(): String? = readLine()
}


sealed class GameState
object InitializedGame : GameState()
object Started : GameState()
object RoundRequested: GameState()
class RoundStarted(val player: Player, val playground: Playground) : GameState()
class Win(val winner: Player, val playground: Playground) : GameState()
class Draw(val playground: Playground) : GameState()


class TicTacToe(
        private val player1: Player,
        private val player2: Player,
        private val gameListener: GameListener = DefaultGameListener()
) : GameListener by gameListener {

    private var currentPlayer = player1
    private val playground = Playground()
    private val inputValidator = InputValidator()

    private var gameState: GameState = InitializedGame
        set(value) {
            field = value
            onGameStateChanged(field)
        }

    private var inputState: InputState =  InitializedInput
        set(value) {
            field = value
            onInputStateChanged(field)
        }


    fun start() {
        gameState = Started
        startNextRound()
    }

    private fun startNextRound() {
        updateGameState()
        if (gameState is RoundRequested) {
            playRound()
            startNextRound()
        }
    }


    private fun updateGameState() {
        gameState = when {
            playground.hasWinState() -> Win(currentPlayer, playground)
            playground.hasDrawState() -> Draw(playground)
            else -> RoundRequested
        }
    }

    private fun playRound() {
        toggleCurrentPlayer()
        gameState = RoundStarted(currentPlayer, playground)
        processPlayerInput()
    }

    private fun processPlayerInput() {
        val choice = getPlayersChoice()
        playground[choice] = currentPlayer.symbol
    }

    private fun getPlayersChoice(): Cell {
        requestInput()
        return when(val inputState = inputState) {
            is ValidInput -> Cell(inputState.rowIndex, inputState.colIndex)
            else -> this.getPlayersChoice()
        }
    }

    private fun requestInput() {
        inputState = Requesting(currentPlayer, playground)
        inputState = inputValidator.getErrorOf(onInputRequested(), playground)
    }

    private fun toggleCurrentPlayer() {
        currentPlayer = if (currentPlayer == player1) player2 else player1
    }


}