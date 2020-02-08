package be.codingtim.velo.sensor.service.web

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/test")
class HelloController {

    @RequestMapping(method = [RequestMethod.GET])
    fun hello(): String {
        return "Hello world"
    }
}