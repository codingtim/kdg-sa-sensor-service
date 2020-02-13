package be.codingtim.velo.sensor.service.messaging

import com.rabbitmq.client.ConnectionFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration


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
}