package functions.exercise2.task

import kotlin.random.Random


data class Player(val name: String)
open class Symbol(val identifier: Char)
object O : Symbol('O')
object X : Symbol('X')
object Empty : Symbol(' ')

class DrawException : Exception("The game is a draw")

class TicTacToe(private val player1: Player, private val player2: Player) {

    private var currentPlayer = if (Random.nextBoolean()) player1 else player2
    private var symbolMap = hashMapOf(
            player1 to O,
            player2 to X
    )

    private val playgound = arrayOf(
            arrayOf<Symbol>(Empty, Empty, Empty),
            arrayOf<Symbol>(Empty, Empty, Empty),
            arrayOf<Symbol>(Empty, Empty, Empty)
    )

    fun play(): Player? {

        println("${player1.name}, du bist an der Reihe.")
        println("Bitte gebe ein, in welche Zelle dein Symbol gesetzt werden soll. Gebe deine Auswahl in folgender Form ein: rowIndex, colIndex")
        println("""
                0   1   2
            0  _${playgound[0][0].identifier}_|_${playgound[0][1].identifier}_|_${playgound[0][2].identifier}_
            1  _${playgound[1][0].identifier}_|_${playgound[1][1].identifier}_|_${playgound[1][2].identifier}_
            2   ${playgound[2][0].identifier} | ${playgound[2][1].identifier} | ${playgound[2][2].identifier}
        """.trimIndent())


        var choice = readLine()
        var indicesString = choice?.split(',')
        var rowIndex = indicesString?.getOrNull(0)?.toIntOrNull()
        var colIndex = indicesString?.getOrNull(1)?.toIntOrNull()
        while (colIndex == null || colIndex !in (0..2) || rowIndex == null || rowIndex !in (0..2) || playgound[rowIndex][colIndex] != Empty) {
            println("Du hast $choice eingegeben. Dabei handelt es sich um keine Valide eingabe.")
            println("Versuche es erneut.")
            choice = readLine()
            indicesString = choice?.split(',')
            rowIndex = indicesString?.getOrNull(0)?.toIntOrNull()
            colIndex = indicesString?.getOrNull(1)?.toIntOrNull()
        }

        symbolMap[currentPlayer]?.let { symbol ->
            playgound[rowIndex][colIndex] = symbol

            if (
                    (playgound[0][0] == symbol && playgound[0][1] == symbol && playgound[0][2] == symbol) // Row 1
                    || (playgound[1][0] == symbol && playgound[1][1] == symbol && playgound[1][2] == symbol) // Row 2
                    || (playgound[2][0] == symbol && playgound[2][1] == symbol && playgound[2][2] == symbol) // Row 3
                    || (playgound[0][0] == symbol && playgound[1][0] == symbol && playgound[2][0] == symbol) // Col 1
                    || (playgound[0][1] == symbol && playgound[1][1] == symbol && playgound[2][1] == symbol) // Col 2
                    || (playgound[0][2] == symbol && playgound[1][2] == symbol && playgound[2][2] == symbol) // Col 3
                    || (playgound[0][0] == symbol && playgound[1][1] == symbol && playgound[2][2] == symbol) // Diag tl - br
                    || (playgound[2][0] == symbol && playgound[1][1] == symbol && playgound[0][2] == symbol) // Diag bl - tr
            ) return currentPlayer

            if (playgound.flatten().none { it == Empty }) throw DrawException()

            currentPlayer = if (currentPlayer == player1) player2 else player1

        } ?: let {
            println("Playersymbol konnte nicht gefunden werden")
            return null
        }

        return null
    }
}

object Game {
    @JvmStatic
    fun main(args: Array<String>) {
        val game = TicTacToe(Player("Paul"), Player("Hans"))
        var winner: Player? = null
        try {
            do {
                winner = game.play()
            } while (winner == null)
            println("${winner.name} hat gewonnen!")
        } catch (ex: DrawException) {
            println("Unentschieden")
        }
    }
}