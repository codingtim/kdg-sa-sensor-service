package be.codingtim.velo.sensor.service.domain

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan(basePackageClasses = [DomainConfiguration::class])
open class DomainConfiguration {

}