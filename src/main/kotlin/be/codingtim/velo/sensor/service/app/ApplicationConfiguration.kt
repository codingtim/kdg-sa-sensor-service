package be.codingtim.velo.sensor.service.app

import be.codingtim.velo.sensor.service.web.WebConfiguration
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(
        WebConfiguration::class
)
open class ApplicationConfiguration