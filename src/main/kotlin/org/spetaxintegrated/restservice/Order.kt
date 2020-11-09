package org.spetaxintegrated.restservice

import com.fasterxml.jackson.annotation.JsonCreator
import org.springframework.beans.factory.annotation.Autowired

data class OrderSuccesful(
        val id: Long,
        val items: Array<String> = emptyArray(),
        val totalPrice: Float,
        val orderNotifier: String

)

// New data class for incoming orders
data class NewOrder @JsonCreator constructor(
        val items: Array<String>
)