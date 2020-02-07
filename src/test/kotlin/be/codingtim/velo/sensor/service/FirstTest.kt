package be.codingtim.velo.sensor.service

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class FirstTest {
    @Test
    internal fun firstTest() {
        val classUnderTest = ClassUnderTest(true)
        assertEquals(true, classUnderTest.runs)
    }
}