package be.codingtim.velo.sensor.service.web

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.config.EnableWebFlux

@Configuration
@EnableWebFlux
@ComponentScan(basePackageClasses = [WebConfiguration::class])
open class WebConfiguration {

}

