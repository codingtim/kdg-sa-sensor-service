package be.codingtim.velo.sensor.service.web

import org.junit.jupiter.api.Test
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
}