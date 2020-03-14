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
    internal fun getAfter() {
        val sensorValue1 = SensorValue(
                Instant.parse("2020-02-11T15:45:00.000Z"),
                "CO2",
                "21.0",
                51.21311111,
                4.59305556
        )
        val sensorValue2 = SensorValue(
                Instant.parse("2020-02-11T15:46:00.000Z"),
                "CO2",
                "22.0",
                51.21311111,
                4.59305556
        )
        val sensorValue3 = SensorValue(
                Instant.parse("2020-02-11T15:47:00.000Z"),
                "CO2",
                "23.0",
                51.21311111,
                4.59305556
        )
        runBlocking {
            sensorValuesStoreDatabase.store(sensorValue1)
            sensorValuesStoreDatabase.store(sensorValue2)
            sensorValuesStoreDatabase.store(sensorValue3)
            val values = sensorValuesStoreDatabase.retrieve(Instant.parse("2020-02-11T15:46:30.000Z"))
            assertEquals(2, values.size)
            assertEquals(sensorValue2, values[0])
            assertEquals(sensorValue1, values[1])
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