package be.codingtim.velo.sensor.service

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class FirstTest {

    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    @Test
    internal fun firstTest() {
        val classUnderTest = ClassUnderTest(true)
        logger.info("running first test")
        assertEquals(true, classUnderTest.runs)
    }
}