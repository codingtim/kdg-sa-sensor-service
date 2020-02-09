package be.codingtim.velo.sensor.service.database

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.awaitFirstOrNull
import org.apache.commons.logging.LogFactory
import org.springframework.data.r2dbc.core.DatabaseClient
import org.springframework.data.r2dbc.core.await

class TableCreator(private val databaseClient: DatabaseClient) {

    private val log = LogFactory.getLog(this::class.java)

    suspend fun createTables(): TableCreator {
        databaseClient.execute("CREATE TABLE SENSOR_READ (id identity primary key auto_increment, title VARCHAR(50) NOT NULL);").await()

        databaseClient.execute("insert into SENSOR_READ (title) values ('bla')")
                .fetch()
                .rowsUpdated()
                .awaitFirstOrNull()

        val result = mutableListOf<SensorRead>()
        databaseClient.execute("select * from SENSOR_READ")
                .map { row, _ -> SensorRead(row?.get("title").toString()) }
                .all()
                .asFlow()
                .collect { result.add(it) }
        println(result)
        log.info("Created tables")

        return this
    }

    data class SensorRead(val title: String)
}