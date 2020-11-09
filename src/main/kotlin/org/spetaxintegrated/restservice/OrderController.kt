package org.spetaxintegrated.restservice

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.concurrent.atomic.AtomicLong

@RestController
class OrderController(@Autowired val orderNotifier: OrderNotifier) {

    val orderCounter = AtomicLong()

    @RequestMapping("/order", method = arrayOf(RequestMethod.POST))
    fun processOrder(@RequestBody newOrder: NewOrder?) : OrderSuccesful {
        var order : OrderSuccesful?

        order = this.createOrderFromRequestUsingOffer(newOrder)

        orderNotifier.orderCompleteListener()
        return order as OrderSuccesful
    }

    @RequestMapping("/orderDiscount", method = arrayOf(RequestMethod.POST))
    fun processOrderDiscount(@RequestBody newOrder: NewOrder?) : OrderSuccesful {
        var order : OrderSuccesful?

         order = this.createOrderFromRequest(newOrder)

        return order as OrderSuccesful
    }

    private fun createOrderFromRequest(newOrder: NewOrder?) : OrderSuccesful? {
        var priceTotal = 0f
        var appleCount = 0
        var orangeCount = 0

        if (newOrder != null) {
            for (item in newOrder.items) {
                if (item.compareTo("Apple", true) == 0)
                    appleCount++
                else if (item.compareTo("Orange", true) == 0)
                    orangeCount++
            }
        }

        priceTotal = (appleCount * 0.60f) + (orangeCount * 0.25f)

        val calculatedItemsList : MutableList<String> = arrayListOf<String>()
        var x = 0
        while (x < appleCount) {
            calculatedItemsList.add("Apple")
            x++
        }
        x = 0
        while (x < orangeCount) {
            calculatedItemsList.add("Orange")
            x++
        }

        val order = OrderSuccesful(
                id = orderCounter.incrementAndGet(),
                items = calculatedItemsList.toTypedArray(),
                totalPrice = priceTotal,
                orderNotifier = "order complete"
        )

        return order
    }

    private fun createOrderFromRequestUsingOffer(newOrder: NewOrder?) : OrderSuccesful? {
        var priceTotal = 0f
        var appleCount = 0
        var orangeCount = 0
        if (newOrder != null) {
            for (item in newOrder.items) {
                if (item.compareTo("Apple", true) == 0)
                    appleCount++
                else if (item.compareTo("Orange", true) == 0)
                    orangeCount++
            }
        }

        priceTotal = ((appleCount / 2) * 0.60f) + ((appleCount % 2) * 0.60f)  +
                ((orangeCount / 3) * 2 * 0.25f) + ((orangeCount % 3) * 0.25f)

        val calculatedItemsList : MutableList<String> = arrayListOf<String>()
        var x = 0
        while (x < appleCount) {
            calculatedItemsList.add("Apple")
            x++
        }
        x = 0
        while (x < orangeCount) {
            calculatedItemsList.add("Orange")
            x++
        }

        val order = OrderSuccesful(
                id = orderCounter.incrementAndGet(),
                items = calculatedItemsList.toTypedArray(),
                totalPrice = priceTotal,
                orderNotifier = "order complete"
        )

        return order
    }


}