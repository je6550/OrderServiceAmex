package org.spetaxintegrated.restservice

import org.springframework.stereotype.Component
import java.nio.channels.Channels

@Component
class OrderNotifier {

    fun orderCompleteListener(){
       println("Your order is complete. The delivery is expected in 24 hours")
   }

    fun orderIncompleteListener(){
        println("Your order wasn't completed")
    }

}