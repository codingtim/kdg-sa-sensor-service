package be.codingtim.velo.sensor.service.domain

import org.springframework.stereotype.Service
import java.time.Instant

data class SensorValue(val timestamp: Instant,
                       val type: String,
                       val value: String,
                       val latitude: Double,
                       val longitude: Double)

interface SensorValues {
    suspend fun add(sensorValue: SensorValue)
    suspend fun getAll(): List<SensorValue>
    suspend fun size(): Long
    suspend fun get(before: Instant, limit: Int): List<SensorValue>
}

@Service
internal class SensorValuesImpl(private val sensorValuesStore: SensorValuesStore) : SensorValues {
    override suspend fun add(sensorValue: SensorValue) {
        sensorValuesStore.store(sensorValue)
    }

    override suspend fun getAll(): List<SensorValue> {
        return sensorValuesStore.retrieveAll()
    }

    override suspend fun get(before: Instant, limit: Int): List<SensorValue> {
        return sensorValuesStore.retrieve(before, limit)
    }

    override suspend fun size(): Long {
        return sensorValuesStore.size()
    }
}

interface SensorValuesStore {
    suspend fun store(sensorValue: SensorValue)
    suspend fun retrieveAll(): List<SensorValue>
    suspend fun retrieve(before: Instant, limit: Int): List<SensorValue>
    suspend fun size(): Long
}