package be.codingtim.velo.sensor.service.web

import be.codingtim.velo.sensor.service.domain.SensorValue
import be.codingtim.velo.sensor.service.domain.SensorValues
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.http.HttpHeaders.CONTENT_TYPE
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.http.codec.json.Jackson2JsonDecoder
import org.springframework.http.codec.json.Jackson2JsonEncoder
import org.springframework.test.web.reactive.server.WebTestClient
import java.time.Clock
import java.time.Instant
import java.time.ZoneId

internal class SensorControllerTest {

    private val sensorValues = DummySensorValues()
    private val controller = SensorController(
            sensorValues,
            Clock.fixed(Instant.parse("2020-03-14T18:50:00.000Z"), ZoneId.of("UTC"))
    )

    private val objectMapper = createObjectMapper()
    private val client = WebTestClient
            .bindToController(controller)
            .httpMessageCodecs { configurer ->
                configurer.defaultCodecs().jackson2JsonDecoder(Jackson2JsonDecoder(objectMapper))
                configurer.defaultCodecs().jackson2JsonEncoder(Jackson2JsonEncoder(objectMapper))
            }
            .build()

    @Test
    internal fun add() = runBlocking {
        client
                .post()
                .uri("/sensor-values")
                .bodyValue("""{
                    | "timestamp": "2020-02-11T15:45:00.000Z",
                    | "type": "CO2",
                    | "value": "21.0",
                    | "latitude": 51.21311111,
                    | "longitude": 4.59305556
                    | }""".trimMargin())
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .exchange()
                .expectStatus().isAccepted

        assertEquals(SensorValue(
                Instant.parse("2020-02-11T15:45:00.000Z"),
                "CO2",
                "21.0",
                51.21311111,
                4.59305556
        ), sensorValues.getAll()[0])
    }

    @Test
    internal fun get() {
        client
                .get()
                .uri("/sensor-values")
                .exchange()
                .expectStatus().isOk
        assertEquals(Instant.parse("2020-03-14T18:50:00.000Z"), sensorValues.after)
        assertEquals(50, sensorValues.limit)
    }

    @Test
    internal fun getBeforeWithLimit() {
        client
                .get()
                .uri("/sensor-values?before=2020-02-11T15:45:00.000Z&limit=10")
                .exchange()
                .expectStatus().isOk
        assertEquals(Instant.parse("2020-02-11T15:45:00.000Z"), sensorValues.after)
        assertEquals(10, sensorValues.limit)
    }

    private class DummySensorValues : SensorValues {
        private val values = mutableListOf<SensorValue>()
        var after: Instant? = null
        var limit: Int? = null

        override suspend fun add(sensorValue: SensorValue) {
            values.add(sensorValue)
        }

        override suspend fun getAll(): List<SensorValue> {
            return values
        }

        override suspend fun get(before: Instant, limit: Int): List<SensorValue> {
            this.after = before;
            this.limit = limit;
            return values
        }

        override suspend fun size(): Long {
            return values.size.toLong()
        }

    }
}