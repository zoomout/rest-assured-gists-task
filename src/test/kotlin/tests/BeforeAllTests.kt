package tests

import SerializationConfiguration.Companion.configureJacksonSerialization
import org.testng.annotations.BeforeSuite

open class BeforeAllTests {
    @BeforeSuite
    fun configureSerialization() {
        configureJacksonSerialization()
    }

}
