package tests

import assertStatusCode
import assertions.assertFilesInResponse
import configuration.authHeader
import createGistAndGetId
import dto.GistRequest
import dto.GistResponse
import getBody
import getGist
import org.apache.http.HttpStatus.SC_OK
import org.testng.annotations.Test
import testdata.GistTestData

class GetGistTest : BeforeAllTests() {

    @Test(dataProvider = "positiveGistData", dataProviderClass = GistTestData::class)
    fun getGistTest(gist: GistRequest) {
        val gistId = createGistAndGetId(gist, authHeader)
        val response = getGist(gistId, authHeader)
        assertStatusCode(response, SC_OK)
        val responseBody = getBody(response, GistResponse::class.java)
        assertFilesInResponse(responseBody.files, gist.files)
    }

}
