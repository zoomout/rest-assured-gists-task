package assertions

import dto.GistFile
import org.testng.Assert

fun assertFilesInResponse(actualFiles: Map<String, GistFile?>?, expectedFiles: Map<String, GistFile?>) {
    actualFiles?.let { nonNullActualFiles ->
        Assert.assertEquals(nonNullActualFiles.keys, expectedFiles.keys, "List of files in response is not as expected")
        val actualContents = nonNullActualFiles.values.map { file -> file?.content }.toList()
        val expectedContents = expectedFiles.values.map { file -> file?.content }.toList()
        Assert.assertEquals(actualContents, expectedContents, "Contents in the file is not as expected")
    } ?: run {
        Assert.fail("Files object in response is null")
    }
}
