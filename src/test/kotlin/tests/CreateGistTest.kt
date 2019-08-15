package tests

import assertStatusCode
import assertions.assertFilesInResponse
import configuration.authHeader
import createGist
import dto.ErrorDto
import dto.GistRequest
import dto.GistResponse
import getBody
import org.apache.http.HttpStatus.SC_CREATED
import org.testng.Assert.assertEquals
import org.testng.annotations.Test
import testdata.GistTestData

class CreateGistTest : BeforeAllTests() {

    @Test(dataProvider = "positiveGistData", dataProviderClass = GistTestData::class)
    fun positiveCreateGistTest(gistToCreate: GistRequest) {
        val response = createGist(gistToCreate, authHeader)
        assertStatusCode(response, SC_CREATED)
        val responseBody = getBody(response, GistResponse::class.java)
        assertFilesInResponse(responseBody.files, gistToCreate.files)
    }

    @Test(dataProvider = "negativeGistData", dataProviderClass = GistTestData::class)
    fun negativeCreateGistTest(gistToCreate: GistRequest, expectedErrorResponse: ErrorDto, expectedCode: Int) {
        val response = createGist(gistToCreate, authHeader)
        assertStatusCode(response, expectedCode)
        val actualErrorResponse = getBody(response, ErrorDto::class.java)
        assertEquals(actualErrorResponse, expectedErrorResponse)
    }

}
