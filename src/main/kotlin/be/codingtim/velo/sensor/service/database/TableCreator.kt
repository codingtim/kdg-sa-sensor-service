package be.codingtim.velo.sensor.service.database

import org.apache.commons.logging.LogFactory
import org.springframework.data.r2dbc.core.DatabaseClient
import org.springframework.data.r2dbc.core.await

class TableCreator(private val databaseClient: DatabaseClient) {

    private val log = LogFactory.getLog(this::class.java)

    suspend fun createTables(): TableCreator {
        databaseClient.execute("""CREATE TABLE SENSOR_VALUE (
                | id identity primary key, 
                | timestamp TIMESTAMP WITH TIME ZONE,
                | type VARCHAR(50),
                | value VARCHAR(50),
                | latitude DOUBLE,
                | longitude DOUBLE
                | );""".trimMargin()).await()

        log.info("Created tables")
        return this
    }

}