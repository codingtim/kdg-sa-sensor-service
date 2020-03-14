package be.codingtim.velo.sensor.service.web

import be.codingtim.velo.sensor.service.domain.SensorValue
import be.codingtim.velo.sensor.service.domain.SensorValues
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
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
    suspend fun get(@RequestParam(required = false) before: Instant?): ResponseEntity<List<SensorValue>> {
        val sensorValues = sensorValues.get(before ?: Instant.now(clock))
        return ResponseEntity.ok().body(sensorValues)
    }

    @RequestMapping(path = ["/size"], method = [RequestMethod.GET],
            produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    suspend fun size(): ResponseEntity<Long> {
        return ResponseEntity.ok().body(sensorValues.size())
    }

}