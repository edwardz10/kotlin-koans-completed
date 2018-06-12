package ii_collections

import v_builders.data.cushion

fun Shop.getCustomersWhoOrderedProduct(product: Product): Set<Customer> {
    // Return the set of customers who ordered the specified product
//    todoCollectionTask()
    return customers.filter { it -> it.orderedProducts.contains(product) }.toSet()
}

fun Customer.getMostExpensiveDeliveredProduct(): Product? {
    // Return the most expensive product among all delivered products
    // (use the Order.isDelivered flag)
//    todoCollectionTask()
    return orders.filter { it -> it.isDelivered }.flatMap { it -> it.products }.maxBy { it -> it.price }
}

fun Shop.getNumberOfTimesProductWasOrdered(product: Product): Int {
    // Return the number of times the given product was ordered.
    // Note: a customer may order the same product for several times.
//    todoCollectionTask()
    return customers.flatMap { it -> it.orders }.flatMap { it -> it.products }.filter { it -> it.equals(product) }.count()
}
