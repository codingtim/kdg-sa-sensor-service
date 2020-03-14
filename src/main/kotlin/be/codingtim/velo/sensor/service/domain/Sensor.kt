package be.codingtim.velo.sensor.service.domain

import org.springframework.stereotype.Service
import java.time.Clock
import java.time.Instant

data class SensorValue(val timestamp: Instant,
                       val type: String,
                       val value: String,
                       val latitude: Double,
                       val longitude: Double)

data class Before(val value: Instant) {
    companion object {
        fun of(value: Instant?, clock: Clock) = Before(value ?: Instant.now(clock))
    }
}

data class Limit(val value: Int) {
    // https://dev.to/flbenz/kotlin-and-domain-driven-design-value-objects-4m32
    init {
        require(value > 0) { "Limit should be positive." }
        require(value < 201) { "Limit should be smaller than 200." }
    }

    companion object {
        fun of(value: Int?) = Limit(value ?: 50)
    }
}

interface SensorValues {
    suspend fun add(sensorValue: SensorValue)
    suspend fun getAll(): List<SensorValue>
    suspend fun size(): Long
    suspend fun get(before: Before, limit: Limit): List<SensorValue>
}

@Service
internal class SensorValuesImpl(private val sensorValuesStore: SensorValuesStore) : SensorValues {
    override suspend fun add(sensorValue: SensorValue) {
        sensorValuesStore.store(sensorValue)
    }

    override suspend fun getAll(): List<SensorValue> {
        return sensorValuesStore.retrieveAll()
    }

    override suspend fun get(before: Before, limit: Limit): List<SensorValue> {
        return sensorValuesStore.retrieve(before, limit)
    }

    override suspend fun size(): Long {
        return sensorValuesStore.size()
    }
}

interface SensorValuesStore {
    suspend fun store(sensorValue: SensorValue)
    suspend fun retrieveAll(): List<SensorValue>
    suspend fun retrieve(before: Before, limit: Limit): List<SensorValue>
    suspend fun size(): Long
}