package konsistenz

/**
 * @author Florian Herborn & Dennis Dubbert
 **/

object EmployeeSolution {

    class Employee {
        fun getName(): String = TODO()

        fun isWorking(): Boolean = TODO()

        fun addEmail(email: String): Unit = TODO()
    }

    fun main() {
        val employee = Employee()

        val name = employee.getName()
        employee.addEmail("max@mustermann.de")
        if (employee.isWorking()) {
            // ...
        }
    }
}