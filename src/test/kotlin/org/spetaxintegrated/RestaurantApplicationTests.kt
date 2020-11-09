package org.spetaxintegrated

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.spetaxintegrated.restservice.NewOrder
import org.spetaxintegrated.restservice.OrderController
import org.spetaxintegrated.restservice.OrderSuccesful


@RunWith(JUnit4::class)
class RestaurantApplicationTests {

	@Mock
	var newOrder: NewOrder? = null


	@Before
	fun setUp() {

		this.newOrder.apply { "Apple" }
		this.newOrder.apply { "Orange" }
	}

	@Test
	fun processOrderTest() {


		val mockOrderController = Mockito.mock(OrderController::class.java)
		Mockito.`when`(mockOrderController.processOrder(newOrder)).thenReturn(OrderSuccesful(1, arrayOf("Apple, Orange"), 1.06F, "order complete"));
	}

	@Test
	fun processOrderDiscountTest() {


		val mockOrderController = Mockito.mock(OrderController::class.java)
		Mockito.`when`(mockOrderController.processOrderDiscount(newOrder)).thenReturn(OrderSuccesful(1, arrayOf("Apple, Apple, Apple"), 1.8F, "order complete"));
	}
}
