package wortspiele

import data.Product
import data.User

/**
 * @author Dennis Dubbert
 * @see 'MARTIN, Robert C. Clean code: A handbook of agile software craftsmanship. Pearson Education, 2009. S.27'
 **/

object PunExample {

    class UserStore {
        private val users : MutableList<User> = TODO()

        fun add(user: User) {
            users.add(user)
        }
    }

    class ProductStore {
        private val products : MutableList<Product> = TODO()

        fun add(product: Product) {
            products.add(0, product)
        }
    }

}