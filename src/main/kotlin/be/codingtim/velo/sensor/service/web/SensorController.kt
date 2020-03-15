package be.codingtim.velo.sensor.service.web

import be.codingtim.velo.sensor.service.domain.Before
import be.codingtim.velo.sensor.service.domain.Limit
import be.codingtim.velo.sensor.service.domain.SensorValue
import be.codingtim.velo.sensor.service.domain.SensorValues
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import java.time.Clock
import java.time.Instant

@RestController
@RequestMapping("/sensor-values")
class SensorController(
        private val sensorValues: SensorValues,
        private val clock: Clock
) {

    @RequestMapping(method = [RequestMethod.POST],
            consumes = [MediaType.APPLICATION_JSON_VALUE],
            produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    suspend fun add(@RequestBody sensorValue: SensorValue): ResponseEntity<Void> {
        sensorValues.add(sensorValue)
        return ResponseEntity.accepted().build()
    }

    @RequestMapping(method = [RequestMethod.GET],
            produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    suspend fun get(
            @RequestParam(required = false) before: Instant?,
            @RequestParam(required = false) limit: Int?
    ): ResponseEntity<List<SensorValue>> {
        val sensorValues = sensorValues.get(
                Before.of(before, clock),
                Limit.of(limit)
        )
        // https://docs.spring.io/spring/docs/current/spring-framework-reference/web-reactive.html#webflux-uri-building
        // https://developer.github.com/v3/#hypermedia
        val previousUri = UriComponentsBuilder.fromPath("sensor-values/")
                .queryParam("before", sensorValues.lastOrNull()?.timestamp)
                .build()
                .toString()
        return ResponseEntity.ok()
                .header("Link", "<$previousUri>; rel=\"prev\"")
                .body(sensorValues)
    }

    @RequestMapping(path = ["/size"], method = [RequestMethod.GET],
            produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    suspend fun size(): ResponseEntity<Long> {
        return ResponseEntity.ok().body(sensorValues.size())
    }

}