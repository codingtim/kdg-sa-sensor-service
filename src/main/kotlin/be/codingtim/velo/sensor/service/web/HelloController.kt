package be.codingtim.velo.sensor.service.web

import kotlinx.coroutines.delay
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/test")
class HelloController(@Value("\${welcome.message}") val message: String) {

    @RequestMapping(method = [RequestMethod.GET], produces = [MediaType.APPLICATION_JSON_VALUE])
    suspend fun hello(): Response {
        delay(200)
        return Response(message)
    }

    data class Response(val message: String)
}