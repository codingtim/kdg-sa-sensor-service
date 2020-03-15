package be.codingtim.velo.sensor.service.web

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.http.ResponseEntity
import org.springframework.http.codec.ServerCodecConfigurer
import org.springframework.http.codec.json.Jackson2JsonDecoder
import org.springframework.http.codec.json.Jackson2JsonEncoder
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.reactive.config.DelegatingWebFluxConfiguration
import org.springframework.web.reactive.config.EnableWebFlux

@Configuration
//https://docs.spring.io/spring/docs/current/spring-framework-reference/web-reactive.html#webflux-config
@EnableWebFlux
@ComponentScan(basePackageClasses = [WebConfiguration::class])
//could be configured in more detail with
//https://docs.spring.io/spring/docs/current/spring-framework-reference/web-reactive.html#webflux-config-advanced-java
open class WebConfiguration: DelegatingWebFluxConfiguration() {

    override fun configureHttpMessageCodecs(configurer: ServerCodecConfigurer) {
        val objectMapper = createObjectMapper()
        configurer.defaultCodecs().jackson2JsonDecoder(Jackson2JsonDecoder(objectMapper))
        configurer.defaultCodecs().jackson2JsonEncoder(Jackson2JsonEncoder(objectMapper))
    }
}

fun createObjectMapper(): ObjectMapper {
    return ObjectMapper()
            .findAndRegisterModules()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
}

@ControllerAdvice
// https://docs.spring.io/spring/docs/current/spring-framework-reference/web-reactive.html#webflux-ann-controller-exceptions
open class ControllerExceptionHandler {

    @ExceptionHandler
    fun illegalArgumentException(exception: IllegalArgumentException): ResponseEntity<String> {
        return ResponseEntity.badRequest().body(exception.message)
    }
}