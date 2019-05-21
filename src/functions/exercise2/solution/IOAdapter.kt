package functions.exercise2.solution

interface IOAdapter {
    infix fun printMessage(message: String)
    infix fun printError(message: String)
    fun readInput(): String?
}

class ConsoleIOAdapter: IOAdapter {
    override fun readInput() = readLine()
    override fun printMessage(message: String) {
        println(message)
    }
    override fun printError(message: String) {
        System.err.println(message)
    }
}
