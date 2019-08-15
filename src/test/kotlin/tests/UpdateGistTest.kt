package tests

import assertStatusCode
import assertions.assertFilesInResponse
import configuration.authHeader
import createGistAndGetId
import dto.GistRequest
import dto.GistResponse
import getBody
import org.apache.http.HttpStatus.SC_OK
import org.testng.annotations.Test
import testdata.GistTestData
import updateGist

class UpdateGistTest : BeforeAllTests() {

    @Test(dataProvider = "positiveGistData", dataProviderClass = GistTestData::class)
    fun updateGistTest(gist: GistRequest) {
        val gistId = createGistAndGetId(gist, authHeader)
        gist.files.values.forEach { file -> file?.content = "${file?.content} is updated" }
        val response = updateGist(gistId, gist, authHeader)
        assertStatusCode(response, SC_OK)
        val responseBody = getBody(response, GistResponse::class.java)
        assertFilesInResponse(responseBody.files, gist.files)
    }

}
