package functions.exercise1.task



data class Product(val price: Double)
data class CreditCard(var limit: Double, var amount: Double)
data class Customer(var creditCard: CreditCard)

class Shop(var products: HashMap<Product, Double>) {

    fun buy(product: Product, customer: Customer, amount: Double) {
        products[product]?.let { productAmount ->
            if(productAmount >= amount) {
                if (customer.creditCard.amount + product.price * amount <= customer.creditCard.limit) {
                    customer.creditCard.amount -= product.price * amount
                    products[product] = productAmount - amount
                }
            }
        }
    }
}