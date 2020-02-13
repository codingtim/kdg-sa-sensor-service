package be.codingtim.velo.sensor.service.web

import be.codingtim.velo.sensor.service.domain.SensorValue
import be.codingtim.velo.sensor.service.domain.SensorValues
import kotlinx.coroutines.delay
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/sensor-values")
class SensorController(private val sensorValues: SensorValues) {

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
    suspend fun getAll(): ResponseEntity<List<SensorValue>> {
        return ResponseEntity.ok().body(sensorValues.getAll())
    }

    @RequestMapping(path = ["/size"], method = [RequestMethod.GET],
            produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    suspend fun size(): ResponseEntity<Long> {
        return ResponseEntity.ok().body(sensorValues.size())
    }

}