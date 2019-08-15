package tests

import assertStatusCode
import configuration.authHeader
import createGistAndGetId
import deleteGist
import dto.GistRequest
import getGist
import org.apache.http.HttpStatus.SC_NOT_FOUND
import org.apache.http.HttpStatus.SC_NO_CONTENT
import org.testng.annotations.Test
import testdata.GistTestData

class DeleteGistTest : BeforeAllTests() {

    @Test(dataProvider = "positiveGistData", dataProviderClass = GistTestData::class)
    fun deleteGistTest(gist: GistRequest) {
        val gistId = createGistAndGetId(gist, authHeader)
        val deleteResponse = deleteGist(gistId, authHeader)
        assertStatusCode(deleteResponse, SC_NO_CONTENT)
        val getResponse = getGist(gistId, authHeader)
        assertStatusCode(getResponse, SC_NOT_FOUND)
    }

}
