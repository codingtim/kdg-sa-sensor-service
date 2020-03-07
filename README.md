# Sensor Service

## Architectural Design Decisions 

### Performance

As this service should process received events as fast as possible we should pick technologies that allow this.
List of technologies used to allow this: 

- Spring Webflux stack running on netty
- non-blocking IO with kotlin coroutines, cleaner and more readable than Flux and Mono types
- database access with r2dbc to allow reactive database access

## Requests

The api can be invoked through HTTP with example call:

    curl -XPOST -H "Content-Type: application/json" -d '{"timestamp": "2020-02-11T15:45:00.000Z","type": "CO2","value": "21.0","latitude": 51.21311111,"longitude": 4.59305556}' 'http://localhost:8080/sensor-values' 
    curl 'http://localhost:8080/sensor-values' 
    curl 'http://localhost:8080/sensor-values/size' 

## Errors and fixes

- [Suspending functions in controller](/doc/errors/suspend-function-controller.md) 