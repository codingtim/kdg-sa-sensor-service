package be.codingtim.velo.sensor.service.web

import be.codingtim.velo.sensor.service.web.HelloController.Request
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.http.HttpHeaders.CONTENT_TYPE
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.test.web.reactive.server.WebTestClient

internal class HelloControllerTest {

    private val controller = HelloController("test message")
    private val client = WebTestClient.bindToController(controller).build()

    @Test
    internal fun hello() {
        client
                .get()
                .uri("/test")
                .exchange()
                .expectStatus().is2xxSuccessful
                .expectBody()
                .jsonPath("$.message").isEqualTo("test message")
    }

    @Test
    internal fun post() {
        client
                .post()
                .uri("/test")
                .bodyValue("{\"message\": \"post some data\"}")
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .exchange()
                .expectStatus().isAccepted
        assertEquals(Request("post some data"), controller.request)
    }
}