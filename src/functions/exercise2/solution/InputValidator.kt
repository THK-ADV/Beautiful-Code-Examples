package functions.exercise2.solution


sealed class InputState
object InitializedInput: InputState()
class Requesting(val player: Player, val playground: Playground): InputState()
class ValidInput(val rowIndex: Int, val colIndex: Int) : InputState()
sealed class ErrorInput(val input: String?): InputState()
class InvalidColumn(input: String?) : ErrorInput(input)
class InvalidRow(input: String?) : ErrorInput(input)
class MissingRow(input: String?) : ErrorInput(input)
class MissingColumn(input: String?) : ErrorInput(input)
class NoInput(input: String?) : ErrorInput(input)
class CellOutOfBounds(input: String?) : ErrorInput(input)
class CellOccupied(input: String?) : ErrorInput(input)

class InputValidator {
    private class SplittedInput private constructor(val rowIndex: String?, val colIndex: String?) {
        companion object{
            fun from(input: String) = SplittedInput(getRowIndexOrNull(input), getColumnIndexOrNull(input))

            private fun getRowIndexOrNull(input: String) = split(input).getOrNull(0)
            private fun getColumnIndexOrNull(input: String) = split(input).getOrNull(1)
            private fun split(input: String) = input.split(',')
        }
    }

    fun getErrorOf(input: String?, currentPlayground: Playground) = getSplittedInput(input).let {
        when  {
            it == null -> NoInput(input)
            it.rowIndex == null -> MissingRow(input)
            it.colIndex == null -> MissingColumn(input)
            it.rowIndex.toIntOrNull() == null ->  InvalidRow(input)
            it.colIndex.toIntOrNull() == null -> InvalidColumn(input)
            !currentPlayground.isOnBoard(it.rowIndex.toInt(), it.colIndex.toInt()) -> CellOutOfBounds(input)
            !currentPlayground.isValidMove(it.rowIndex.toInt(), it.colIndex.toInt()) -> CellOccupied(input)
            else ->  ValidInput(it.rowIndex.toInt(), it.colIndex.toInt())
        }
    }

    private fun getSplittedInput(input: String?) = input?.let { SplittedInput.from(input) }
}