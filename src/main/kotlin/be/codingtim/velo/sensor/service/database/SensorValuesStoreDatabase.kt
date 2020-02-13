package be.codingtim.velo.sensor.service.database

import be.codingtim.velo.sensor.service.domain.SensorValue
import be.codingtim.velo.sensor.service.domain.SensorValuesStore
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.awaitFirstOrNull
import org.springframework.data.r2dbc.core.DatabaseClient
import org.springframework.stereotype.Repository
import java.time.Instant

@Repository
internal class SensorValuesStoreDatabase(private val databaseClient: DatabaseClient) : SensorValuesStore {
    override suspend fun store(sensorValue: SensorValue) {
        databaseClient.execute("insert into SENSOR_VALUE (timestamp, type, value, latitude, longitude) values (:timestamp, :type, :value, :latitude, :longitude)")
                .bind("timestamp", sensorValue.timestamp)
                .bind("type", sensorValue.type)
                .bind("value", sensorValue.value)
                .bind("latitude", sensorValue.latitude)
                .bind("longitude", sensorValue.longitude)
                .fetch()
                .rowsUpdated()
                .awaitFirstOrNull()
    }

    override suspend fun retrieveAll(): List<SensorValue> {
        val result = mutableListOf<SensorValue>()
        databaseClient.execute("select * from SENSOR_VALUE")
                .map { row, _ ->
                    SensorValue(
                            row.get("timestamp", Instant::class.java)!!,
                            row.get("type", String::class.java)!!,
                            row.get("value", String::class.java)!!,
                            row.get("latitude", java.lang.Double::class.java)!!.toDouble(),
                            row.get("longitude", java.lang.Double::class.java)!!.toDouble()
                    )
                }
                .all()
                .asFlow()
                .collect { result.add(it) }
        return result
    }

    override suspend fun size(): Long {
        return databaseClient.execute("select count(*) from SENSOR_VALUE")
                .map { row, _ -> row.get(0, java.lang.Long::class.java)?.toLong() ?: 0 }
                .one()
                .awaitFirstOrNull() ?: 0
    }
}