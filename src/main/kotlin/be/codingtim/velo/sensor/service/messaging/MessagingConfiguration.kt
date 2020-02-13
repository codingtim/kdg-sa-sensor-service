package be.codingtim.velo.sensor.service.messaging

import be.codingtim.velo.sensor.service.domain.SensorValues
import be.codingtim.velo.sensor.service.web.createObjectMapper
import com.fasterxml.jackson.databind.ObjectMapper
import com.rabbitmq.client.ConnectionFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.DependsOn


@Configuration
@ComponentScan(basePackageClasses = [MessagingConfiguration::class])
//https://www.rabbitmq.com/api-guide.html
open class MessagingConfiguration {

    @Bean
    open fun connectionFactoryRabbitMq(
            @Value("\${rabbitmq.username}") username: String,
            @Value("\${rabbitmq.password}") password: String,
            @Value("\${rabbitmq.virtualHost}") virtualHost: String,
            @Value("\${rabbitmq.host}") host: String,
            @Value("\${rabbitmq.port}") port: Int
    ): ConnectionFactory {
        val factory = ConnectionFactory()
        factory.username = username
        factory.password = password
        factory.virtualHost = virtualHost
        factory.host = host
        factory.port = port
        return factory
    }

    @Bean
    open fun queueCreator(connectionFactory: ConnectionFactory,
                          @Value("\${rabbitmq.exchange}") exchange: String,
                          @Value("\${rabbitmq.queue}") queue: String,
                          @Value("\${rabbitmq.routingKey}") routingKey: String
    ): QueueCreator {
        return QueueCreator().init(connectionFactory, exchange, queue, routingKey)
    }

    @Bean
    @DependsOn("queueCreator")
    open fun sensorValueMessageConsumer(connectionFactory: ConnectionFactory,
                                        sensorValues: SensorValues,
                                        @Value("\${rabbitmq.queue}") queue: String): SensorValueMessageConsumer {
        // https://www.rabbitmq.com/api-guide.html#consuming
        val connection = connectionFactory.newConnection()
        val channel = connection.createChannel()
        val sensorValueMessageConsumer = SensorValueMessageConsumer(sensorValues, createObjectMapper(), channel)
        channel.basicConsume(queue, true, sensorValueMessageConsumer)
        return sensorValueMessageConsumer
    }
}