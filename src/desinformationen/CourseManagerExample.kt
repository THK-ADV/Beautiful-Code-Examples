package desinformationen

import data.Course
import data.Student

/**
 * @author Dennis Dubbert & Dennis Dubbert
 * @see 'MARTIN, Robert C. Clean code: A handbook of agile software craftsmanship. Pearson Education, 2009. S.20'
 **/

object CourseManagerExample {

    class CourseManager {
        val courseArray: MutableList<Course> = TODO()
        val enrolledStudentsWithValidRegistrations: MutableList<Student> = TODO()
        val enrolledStudentsWithoutValidRegistrations: MutableList<Student> = TODO()
    }
}
