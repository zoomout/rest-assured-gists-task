package tests

import assertStatusCode
import configuration.authHeader
import createGist
import dto.ErrorDto
import dto.GistRequest
import getBody
import gistsBaseUrl
import io.restassured.RestAssured
import io.restassured.http.Header
import org.apache.http.HttpHeaders
import org.apache.http.HttpStatus.SC_BAD_REQUEST
import org.apache.http.HttpStatus.SC_UNAUTHORIZED
import org.hamcrest.CoreMatchers.`is`
import org.testng.Assert
import org.testng.annotations.Test
import testdata.documentationUrl

class EdgeCasesTests : BeforeAllTests() {

    @Test
    fun failedAuthorizationTest() {
        val response = createGist(GistRequest(mapOf()), "invalidAuthHeader")
        assertStatusCode(response, SC_UNAUTHORIZED)
        val actualErrorResponse = getBody(response, ErrorDto::class.java)
        val expectedErrorResponse = ErrorDto(
            message = "Requires authentication",
            documentationUrl = documentationUrl
        )
        Assert.assertEquals(actualErrorResponse, expectedErrorResponse)

    }

    @Test
    fun invalidContentInPayloadTest() {
        val response = RestAssured.given()
            .header(Header(HttpHeaders.AUTHORIZATION, authHeader))
            .body("string")
            .post(gistsBaseUrl)
            .then()
            .assertThat().body("message", `is`("Problems parsing JSON"))
        assertStatusCode(response, SC_BAD_REQUEST)
        val actualErrorResponse = getBody(response, ErrorDto::class.java)
        val expectedErrorResponse = ErrorDto(
            message = "Problems parsing JSON",
            documentationUrl = documentationUrl
        )
        Assert.assertEquals(actualErrorResponse, expectedErrorResponse)

    }

}
