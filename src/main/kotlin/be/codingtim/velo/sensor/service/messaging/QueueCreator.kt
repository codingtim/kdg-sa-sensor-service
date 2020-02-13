package be.codingtim.velo.sensor.service.messaging

import com.rabbitmq.client.Channel
import com.rabbitmq.client.ConnectionFactory
import org.apache.commons.logging.LogFactory

class QueueCreator {
    private val log = LogFactory.getLog(this::class.java)

    fun init(
            connectionFactory: ConnectionFactory,
            exchange: String,
            queue: String,
            routingKey: String
    ): QueueCreator {
        withChannel(connectionFactory) { channel: Channel ->
            channel.exchangeDeclare(exchange, "direct", true)
            channel.queueDeclare(queue, true, false, false, null)
            channel.queueBind(queue, exchange, routingKey)
        }
        log.info("Created queues")
        return this
    }

    private fun withChannel(connectionFactory: ConnectionFactory, behavior: (Channel) -> Unit) {
        val connection = connectionFactory.newConnection()
        connection.use {
            val channel = connection.createChannel()
            channel.use {
                behavior.invoke(channel)
            }
        }
    }
}