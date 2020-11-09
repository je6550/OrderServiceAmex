package org.spetaxintegrated

import java.lang.Thread
import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class RestaurantApplication {

	val eventsQueueDurable : Boolean = false
	val eventsQueueName : String = "eventsQueue"

	@Bean
	fun runner(template : RabbitTemplate) : ApplicationRunner {
		return ApplicationRunner { args: ApplicationArguments? ->
			template.convertAndSend("eventsQueue", "Hello, world!")
			Thread.sleep(2000)
		}
	}

	@Bean
	fun eventsQueue() : Queue {
		return Queue(eventsQueueName, eventsQueueDurable)
	}

	@RabbitListener(queues = ["eventsQueue"])
	fun listen(input : String) {
		print("Message read from eventsQueue : " + input)
	}


}


fun main(args: Array<String>) {
	runApplication<RestaurantApplication>(*args)
}