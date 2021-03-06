package be.codingtim.velo.sensor.service.app

import be.codingtim.velo.sensor.service.database.DatabaseConfiguration
import be.codingtim.velo.sensor.service.domain.DomainConfiguration
import be.codingtim.velo.sensor.service.messaging.MessagingConfiguration
import be.codingtim.velo.sensor.service.web.WebConfiguration
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.context.annotation.PropertySource

@Configuration
@Import(
        DomainConfiguration::class,
        DatabaseConfiguration::class,
        WebConfiguration::class,
        MessagingConfiguration::class
)
//https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#beans-value-annotations
@PropertySource("classpath:application.properties")
open class ApplicationConfiguration