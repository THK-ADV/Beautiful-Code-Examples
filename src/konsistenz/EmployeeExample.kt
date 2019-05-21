package konsistenz

/**
 * @author Florian Herborn & Dennis Dubbert
 **/

object EmployeeExample {

    class Employee {
        fun name(): String = TODO()

        fun working(): Boolean = TODO()

        fun email(email: String): Unit = TODO()
    }

    fun main() {
        val employee = Employee()

        val name = employee.name()
        employee.email("max@mustermann.de")
        if (employee.working()) {
            // ...
        }
    }
}