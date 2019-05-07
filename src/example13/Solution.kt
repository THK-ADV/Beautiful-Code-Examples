package example13

/**
 * Function Names
 * @author Florian Herborn
 **/
object Solution {

    class Employee {
        fun setName(name: String): Unit = TODO()
        fun getName(): String = TODO()
        fun isWorking(): Boolean = TODO()
        fun addEmail(email: String): Unit = TODO()
    }

    fun main() {
        val employee = Employee()

        val name = employee.getName()
        employee.setName(name)
        employee.addEmail("max@mustermann.de")
        if (employee.isWorking()) {
            // ...
        }
    }
}