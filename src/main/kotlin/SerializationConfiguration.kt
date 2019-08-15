import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.restassured.RestAssured
import io.restassured.config.ObjectMapperConfig
import io.restassured.config.RestAssuredConfig

class SerializationConfiguration {

    companion object {
        fun configureJacksonSerialization() {
            RestAssured.config = RestAssuredConfig.config()
                .objectMapperConfig(ObjectMapperConfig().jackson2ObjectMapperFactory { _, _ ->
                    jacksonObjectMapper()
                        .setSerializationInclusion(JsonInclude.Include.NON_ABSENT)
                        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                })
        }
    }

}
