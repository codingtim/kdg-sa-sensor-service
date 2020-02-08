package be.codingtim.velo.sensor.service.web

import kotlinx.coroutines.delay
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/test")
class HelloController {

    @RequestMapping(method = [RequestMethod.GET])
    suspend fun hello(): String {
        delay(200)
        return "Hello world"
    }
}