package be.codingtim.velo.sensor.service.domain

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import java.time.Clock

@Configuration
@ComponentScan(basePackageClasses = [DomainConfiguration::class])
open class DomainConfiguration {

    @Bean
    open fun clock(): Clock = Clock.systemUTC()
}