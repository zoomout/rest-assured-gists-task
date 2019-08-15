package testdata

import dto.ErrorDto
import dto.ErrorResponseObject
import dto.GistFile
import dto.GistRequest
import org.apache.http.HttpStatus.SC_UNPROCESSABLE_ENTITY
import org.testng.annotations.DataProvider

class GistTestData {

    @DataProvider
    fun positiveGistData(): Array<GistRequest> {
        return arrayOf(
            GistRequest(
                mapOf(
                    "file.txt" to GistFile("content")
                )
            ),
            GistRequest(
                mapOf(
                    "file1.txt" to GistFile("content1"),
                    "file2.txt" to GistFile("content2")
                )
            )
        )
    }

    @DataProvider
    fun negativeGistData(): Array<Array<Any>> {
        return arrayOf(
            arrayOf(
                GistRequest(
                    mapOf(
                        "file.txt" to null
                    )
                ),
                ErrorDto(
                    message = "Validation Failed",
                    errors = listOf(
                        ErrorResponseObject(
                            resource = "Gist",
                            code = "missing_field",
                            field = "files"
                        )
                    ),
                    documentationUrl = documentationUrl
                ),
                SC_UNPROCESSABLE_ENTITY
            ),
            arrayOf(
                GistRequest(
                    mapOf(
                        "file.txt" to GistFile(null)
                    )
                ),
                ErrorDto(
                    message = "Invalid request.\n\n\"content\" wasn't supplied.",
                    documentationUrl = documentationUrl
                ),
                SC_UNPROCESSABLE_ENTITY
            )
        )
    }

}
