package be.codingtim.velo.sensor.service.web

import kotlinx.coroutines.delay
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/test")
class HelloController(@Value("\${welcome.message}") val message: String) {

    var request = Request("")

    @RequestMapping(method = [RequestMethod.GET], produces = [MediaType.APPLICATION_JSON_VALUE])
    suspend fun hello(): Response {
        delay(200)
        return Response(message)
    }

    @RequestMapping(method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun post(@RequestBody request: Request): ResponseEntity<Void> {
        this.request = request
        return ResponseEntity.accepted().build()
    }

    data class Request(val message: String)

    data class Response(val message: String)
}