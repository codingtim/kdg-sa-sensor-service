package be.codingtim.velo.sensor.service.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.Clock
import java.time.Instant
import java.time.ZoneId

internal class SensorTest {

    @Nested
    inner class BeforeTest {

        @Test
        internal fun of() {
            val before = Before(Instant.parse("2020-03-14T19:24:00.000Z"))
            assertEquals(Instant.parse("2020-03-14T19:24:00.000Z"), before.value)
        }

        @Test
        internal fun default() {
            val clock = Clock.fixed(Instant.parse("2020-03-14T19:40:00.000Z"), ZoneId.of("UTC"))
            val before = Before.of(null, clock)
            assertEquals(Instant.parse("2020-03-14T19:40:00.000Z"), before.value)
        }

    }

    @Nested
    inner class LimitTest {
        @Test
        internal fun zero() {
            assertThrows<IllegalArgumentException> { Limit(0) }
        }

        @Test
        internal fun negative() {
            assertThrows<IllegalArgumentException> { Limit(-1) }
        }

        @Test
        internal fun ten() {
            val limit = Limit(10)
            assertEquals(10, limit.value)
        }

        @Test
        internal fun default() {
            val limit = Limit.of(null)
            assertEquals(50, limit.value)
        }
    }

}