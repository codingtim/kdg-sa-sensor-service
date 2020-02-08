package be.codingtim.velo.sensor.service.app

import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.http.server.reactive.HttpHandler
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter
import org.springframework.web.server.adapter.WebHttpHandlerBuilder
import reactor.netty.http.server.HttpServer
import java.util.concurrent.CountDownLatch

fun main() {
    val countDownLatch = CountDownLatch(1)
    val context = AnnotationConfigApplicationContext(ApplicationConfiguration::class.java)
    val handler: HttpHandler = WebHttpHandlerBuilder.applicationContext(context).build()
    val adapter = ReactorHttpHandlerAdapter(handler)
    val environment = context.environment
    //https://docs.spring.io/spring/docs/current/spring-framework-reference/web-reactive.html#webflux-httphandler
    HttpServer.create()
            .host(environment.getProperty("netty.host", "localhost"))
            .port((environment.getProperty("netty.port", "8082").toInt()))
            .handle(adapter)
            .bindNow()
    countDownLatch.await()
}