package dto

import com.fasterxml.jackson.annotation.JsonProperty

data class ErrorDto(
    var message: String? = null,
    var errors: List<ErrorResponseObject>? = null,
    @JsonProperty("documentation_url")
    var documentationUrl: String? = null
)

data class ErrorResponseObject(
    var resource: String? = null,
    var code: String? = null,
    var field: String? = null
)
