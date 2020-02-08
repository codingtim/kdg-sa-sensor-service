package be.codingtim.velo.sensor.service.web

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.config.EnableWebFlux

@Configuration
//https://docs.spring.io/spring/docs/current/spring-framework-reference/web-reactive.html#webflux-config
@EnableWebFlux
@ComponentScan(basePackageClasses = [WebConfiguration::class])
//could be configured in more detail with
//https://docs.spring.io/spring/docs/current/spring-framework-reference/web-reactive.html#webflux-config-advanced-java
open class WebConfiguration {

}

