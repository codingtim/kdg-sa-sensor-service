package be.codingtim.velo.sensor.service.database

import be.codingtim.velo.sensor.service.domain.SensorValue
import kotlinx.coroutines.reactive.awaitFirst
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.r2dbc.core.DatabaseClient
import org.springframework.data.r2dbc.query.Criteria
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.time.Instant

@ExtendWith(SpringExtension::class)
@ContextConfiguration(classes = [DatabaseConfiguration::class])
internal class SensorValuesStoreDatabaseTest(
        @Autowired private val databaseClient: DatabaseClient,
        @Autowired private val sensorValuesStoreDatabase: SensorValuesStoreDatabase
) {

    @BeforeEach
    internal fun setUp() {
        runBlocking {
            databaseClient.execute("delete from SENSOR_VALUE").fetch().rowsUpdated().awaitFirst()
        }
    }

    @Test
    internal fun storeSensorValue() {
        val sensorValue = SensorValue(
                Instant.parse("2020-02-11T15:45:00.000Z"),
                "CO2",
                "21.0",
                51.21311111,
                4.59305556
        )
        runBlocking {
            sensorValuesStoreDatabase.store(sensorValue)
            val values = sensorValuesStoreDatabase.retrieveAll()
            assertEquals(1, values.size)
            assertEquals(sensorValue, values[0])
        }
    }

    @Test
    internal fun size() {
        val sensorValue = SensorValue(
                Instant.parse("2020-02-11T15:45:00.000Z"),
                "CO2",
                "21.0",
                51.21311111,
                4.59305556
        )
        runBlocking {
            sensorValuesStoreDatabase.store(sensorValue)
            val size = sensorValuesStoreDatabase.size()
            assertEquals(1, size)
        }
    }

}