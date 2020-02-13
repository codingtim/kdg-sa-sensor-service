package be.codingtim.velo.sensor.service.messaging

import be.codingtim.velo.sensor.service.domain.SensorValue
import be.codingtim.velo.sensor.service.domain.SensorValues
import com.fasterxml.jackson.databind.ObjectMapper
import com.rabbitmq.client.AMQP
import com.rabbitmq.client.Channel
import com.rabbitmq.client.DefaultConsumer
import com.rabbitmq.client.Envelope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SensorValueMessageConsumer(
        private val sensorValues: SensorValues,
        private val objectMapper: ObjectMapper,
        channel: Channel
) : DefaultConsumer(channel) {
    override fun handleDelivery(consumerTag: String?, envelope: Envelope?, properties: AMQP.BasicProperties?, body: ByteArray?) {
        GlobalScope.launch {
            val sensorValue = objectMapper.readValue(body, SensorValue::class.java)
            sensorValues.add(sensorValue)
        }
    }
}