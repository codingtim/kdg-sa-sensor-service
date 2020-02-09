package be.codingtim.velo.sensor.service.database

import io.r2dbc.spi.ConnectionFactories
import io.r2dbc.spi.ConnectionFactory
import kotlinx.coroutines.runBlocking
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.r2dbc.core.DatabaseClient


@Configuration
@ComponentScan(basePackageClasses = [DatabaseConfiguration::class])
//https://r2dbc.io/
//https://github.com/r2dbc/r2dbc-client
//https://github.com/r2dbc/r2dbc-h2
//https://docs.spring.io/spring-data/r2dbc/docs/1.0.x/reference/html/#r2dbc.core
//https://spring.io/blog/2019/04/12/going-reactive-with-spring-coroutines-and-kotlin-flow
//https://www.baeldung.com/spring-boot-kotlin-coroutines
//https://kotlinlang.org/docs/reference/coroutines/flow.html#flows
open class DatabaseConfiguration {

    @Bean
    open fun connectionFactory(): ConnectionFactory {
        return ConnectionFactories.get("r2dbc:h2:mem:///sensor?options=DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE")
    }

    @Bean
    open fun databaseClient(connectionFactory: ConnectionFactory): DatabaseClient {
        return DatabaseClient.create(connectionFactory)
    }

    @Bean
    open fun tableCreator(databaseClient: DatabaseClient): TableCreator {
        return runBlocking {
            TableCreator(databaseClient).createTables()
        }
    }
}

