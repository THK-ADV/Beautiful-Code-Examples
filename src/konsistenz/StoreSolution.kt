package konsistenz

import data.Product
import data.User
import data.Comment
import data.Task

/**
 * @author Florian Herborn & Dennis Dubbert
 * @see 'MARTIN, Robert C. Clean code: A handbook of agile software craftsmanship. Pearson Education, 2009. S.26'
 **/

object StoreSolution {

    class UserStore {
        private val users : MutableList<User> = TODO()

        fun getUsers(): MutableList<User> = users
    }

    class ProductStore {
        private val products : MutableList<Product> = TODO()

        fun getProducts(): MutableList<Product> = products
    }

    class CommentStore {
        private val comments : MutableList<Comment> = TODO()

        fun getComments(): MutableList<Comment> = comments
    }

    class TaskStack {
        private val tasks : MutableList<Task> = TODO()

        fun pop() : Task {
            if (tasks.isEmpty()) throw NoSuchElementException()

            val task = tasks.last()
            tasks.removeAt(tasks.size - 1)

            return task
        }
    }
}