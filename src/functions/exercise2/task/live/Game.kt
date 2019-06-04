//package functions.exercise2.task.live
//
//import kotlin.random.Random
//
//
//data class Player(val name: String)
//open class Symbol(val identifier: Char)
//object O : Symbol('O')
//object X : Symbol('X')
//object Empty : Symbol(' ')
//
//class DrawException : Exception("The game is a draw")
//
//data class Indicies(val rowIndex: Int?, val colIndex: Int?)
//
//class TicTacToe(private val player1: Player, private val player2: Player) {
//
//    private var currentPlayer = if (Random.nextBoolean()) player1 else player2
//    private var symbolMap = hashMapOf(
//            player1 to O,
//            player2 to X
//    )
//
//    private val playground = arrayOf(
//            arrayOf<Symbol>(Empty, Empty, Empty),
//            arrayOf<Symbol>(Empty, Empty, Empty),
//            arrayOf<Symbol>(Empty, Empty, Empty)
//    )
//
//    fun play(): Player? {
//
//        printInputHint()
//        printPlayground()
//
//        var choice = readLine()
//        var indicies = validatePlayersChoice(choice)
//        while (indicies.colIndex == null || indicies.colIndex !in (0..2) || indicies.rowIndex == null || indicies.rowIndex!in (0..2)
//                || playground[indicies.rowIndex][indicies.colIndex] != Empty) {
//
//            println("Du hast $choice eingegeben. Dabei handelt es sich um keine valide Eingabe.")
//            println("Versuche es erneut.")
//            choice = readLine()
//            indicies = validatePlayersChoice(choice)
//        }
//
//        symbolMap[currentPlayer]?.let { symbol ->
//            playground[indicies.rowIndex][indicies.colIndex] = symbol
//
//            if (hasPlayerWon(symbol))
//                return currentPlayer //winner
//
//            if (playground.flatten().none { it == Empty }) throw DrawException() //draw
//
//            currentPlayer = if (currentPlayer == player1) player2 else player1 //next turn
//
//        } ?: let {
//            println("Playersymbol konnte nicht gefunden werden")
//            return null
//        }
//
//        return null
//    }
//
//    fun hasPlayerWon(symbol: Symbol): Boolean {
//        return (playground[0][0] == symbol && playground[0][1] == symbol && playground[0][2] == symbol) // Row 1
//                || (playground[1][0] == symbol && playground[1][1] == symbol && playground[1][2] == symbol) // Row 2
//                || (playground[2][0] == symbol && playground[2][1] == symbol && playground[2][2] == symbol) // Row 3
//                || (playground[0][0] == symbol && playground[1][0] == symbol && playground[2][0] == symbol) // Col 1
//                || (playground[0][1] == symbol && playground[1][1] == symbol && playground[2][1] == symbol) // Col 2
//                || (playground[0][2] == symbol && playground[1][2] == symbol && playground[2][2] == symbol) // Col 3
//                || (playground[0][0] == symbol && playground[1][1] == symbol && playground[2][2] == symbol) // Diag tl - br
//                || (playground[2][0] == symbol && playground[1][1] == symbol && playground[0][2] == symbol) // Diag bl - tr
//    }
//
//    fun validatePlayersChoice(choice: String?) : Indicies {
//        val indicesString = choice?.split(',')
//        val rowIndex = indicesString?.getOrNull(0)?.toIntOrNull()
//        val colIndex = indicesString?.getOrNull(1)?.toIntOrNull()
//        return Indicies(rowIndex, colIndex)
//    }
//
//    private fun printInputHint() {
//        println("${player1.name}, du bist an der Reihe.")
//        println("Bitte gebe ein, in welche Zelle dein Symbol gesetzt werden soll. Gebe deine Auswahl in folgender Form ein: rowIndex, colIndex")
//    }
//
//    private fun printPlayground() {
//        println("""
//                    0   1   2
//                0  _${playground[0][0].identifier}_|_${playground[0][1].identifier}_|_${playground[0][2].identifier}_
//                1  _${playground[1][0].identifier}_|_${playground[1][1].identifier}_|_${playground[1][2].identifier}_
//                2   ${playground[2][0].identifier} | ${playground[2][1].identifier} | ${playground[2][2].identifier}
//            """.trimIndent())
//    }
//}
//
//object Game {
//    @JvmStatic
//    fun main(args: Array<String>) {
//        val game = TicTacToe(Player("Paul"), Player("Hans"))
//        var winner: Player? = null
//        try {
//            do {
//                winner = game.play()
//            } while (winner == null)
//            println("${winner.name} hat gewonnen!")
//        } catch (ex: DrawException) {
//            println("Unentschieden")
//        }
//    }
//}