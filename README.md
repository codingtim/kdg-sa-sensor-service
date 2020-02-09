# Sensor Service

## Architectural Design Decisions 

### Performance

As this service should process received events as fast as possible we should pick technologies that allow this.
List of technologies used to allow this: 

- Spring Webflux stack running on netty
- non-blocking IO with kotlin coroutines, cleaner and more readable than Flux and Mono types
- database access with r2dbc to allow reactive database access

## Errors and fixes

- [Suspending functions in controller](/doc/errors/suspend-function-controller.md) 