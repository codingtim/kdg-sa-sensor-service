package be.codingtim.velo.sensor.service.web

import kotlinx.coroutines.delay
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/test")
class HelloController(@Value("\${welcome.message}") val message: String) {

    @RequestMapping(method = [RequestMethod.GET])
    suspend fun hello(): String {
        delay(200)
        return message
    }
}