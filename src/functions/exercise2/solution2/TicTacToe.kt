package functions.exercise2.solution2


interface GameListener {
    fun onGameStateChanged(gameState: GameState)
    fun onInputStateChanged(inputState: InputState)
}

interface InputAdapter {
    fun getInput(): String?
}

class ConsoleInputAdapter: InputAdapter {
    override fun getInput() = readLine()
}

sealed class GameState
object InitializedGame : GameState()
object Started : GameState()
object RoundRequested: GameState()
class RoundStarted(val player: Player, val playground: Playground) : GameState()
class Win(val winner: Player, val playground: Playground) : GameState()
class Draw(val playground: Playground) : GameState()


interface Observable<T> {
    fun removeObserver(listener: T)
    fun addObserver(listener: T)
}

interface PlayerSupplier {
    fun getFirstTurnPlayer(): Player
    fun getCurrentPlayer(): Player
    fun switchToNextPlayer()
}

class RoundRobinPlayerSupplier(private val player1: Player, private val player2: Player): PlayerSupplier {

    private var currentPlayer = getFirstTurnPlayer()

    override fun getFirstTurnPlayer() = player1

    override fun getCurrentPlayer() = currentPlayer

    override fun switchToNextPlayer() {
        currentPlayer = if(currentPlayer == player1) player2 else player1
    }
}

class TicTacToe(
        private val playerSupplier: PlayerSupplier,
        private val inputAdapter: InputAdapter = ConsoleInputAdapter()
): Observable<GameListener>{

    private val gameListeners = mutableListOf<GameListener>()
    private val playground = Playground()
    private val inputValidator = InputValidator()

    private var gameState: GameState = InitializedGame
        set(value) {
            field = value
            gameListeners.forEach { it.onGameStateChanged(field) }
        }

    private var inputState: InputState =  InitializedInput
        set(value) {
            field = value
            gameListeners.forEach { it.onInputStateChanged(field) }
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
            playground.hasWinState() -> Win(playerSupplier.getCurrentPlayer(), playground)
            playground.hasDrawState() -> Draw(playground)
            else -> RoundRequested
        }
    }

    private fun playRound() {
        playerSupplier.switchToNextPlayer()
        gameState = RoundStarted(playerSupplier.getCurrentPlayer(), playground)
        processPlayerInput()
    }

    private fun processPlayerInput() {
        val choice = getPlayersChoice()
        playground[choice] = playerSupplier.getCurrentPlayer().symbol
    }

    private fun getPlayersChoice(): Cell {
        requestInput()
        return when(val inputState = inputState) {
            is ValidInput -> Cell(inputState.rowIndex, inputState.colIndex)
            else -> this.getPlayersChoice()
        }
    }

    private fun requestInput() {
        inputState = Requesting(playerSupplier.getCurrentPlayer(), playground)
        inputState = inputValidator.getErrorOf(inputAdapter.getInput(), playground)
    }


    override fun addObserver(listener: GameListener) {
        gameListeners.add(listener)
    }

    override fun removeObserver(listener: GameListener) {
        gameListeners.remove(listener)
    }


}