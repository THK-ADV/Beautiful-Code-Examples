package example13

/**
 * Function Names
 * @author Florian Herborn
 **/
object Example13 {

    class Employee {
        fun name(name: String): Unit = TODO()
        fun name(): String = TODO()
        fun working(): Boolean = TODO()
        fun email(email: String): Unit = TODO()
    }

    fun main() {
        val employee = Employee()

        val name = employee.name()
        employee.name(name)
        employee.email("max@mustermann.de")
        if (employee.working()) {
            // ...
        }
    }
}