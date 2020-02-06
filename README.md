# Sensor Service

## Architectural Design Decisions 

### Performance

As this service should process received events as fast as possible we should pick technologies that allow this.
List of technologies that would allow this: 

- non-blocking IO with kotlin coroutines and r2dbc
- Spring Webflux stack running on netty

Resources:  
https://github.com/r2dbc/r2dbc-client  
https://docs.spring.io/spring/docs/current/spring-framework-reference/web-reactive.html#webflux-httphandler  
