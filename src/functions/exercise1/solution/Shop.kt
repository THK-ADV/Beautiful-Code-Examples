package functions.exercise1.solution


data class Product(val name: Double, val price: Double)
data class CreditCard(var limit: Double, var amount: Double)
data class Customer(var creditCard: CreditCard)
class NotEnoughMoneyException : Exception("Not enough money to pay the products")
class ProductDoesntExistException : Exception("Not enough money to pay the products")

class Shop(private var stock: HashMap<Product, Double>) {

    fun buy(product: Product, customer: Customer, amount: Double) {
        checkAvailability(product, amount)
        val price = calculatePrice(product, amount)
        processPayment(customer, price)
        decrementProductAmount(product, amount)
    }

    private fun processPayment(customer: Customer, price: Double) {
        if (isLiquid(customer, price)) {
            debit(customer, price)
        } else throw NotEnoughMoneyException()
    }

    private fun checkAvailability(product: Product, amount: Double) = getAmountOf(product) >= amount

    private fun getAmountOf(product: Product) = stock[product] ?: throw ProductDoesntExistException()

    private fun debit(customer: Customer, price: Double) {
        customer.creditCard.amount -= price
    }

    private fun isLiquid(customer: Customer, price: Double) =
            customer.creditCard.amount + price <= customer.creditCard.limit

    private fun calculatePrice(product: Product, amount: Double) = product.price * amount

    private fun decrementProductAmount(product: Product, amount: Double) {
        stock[product] = getAmountOf(product) - amount
    }
}