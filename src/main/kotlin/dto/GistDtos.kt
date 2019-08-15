package dto

data class GistRequest(
    var files: Map<String, GistFile?>
)

data class GistResponse(
    var id: String? = null,
    var files: Map<String, GistFile?>? = null
)

data class GistFile(
    var content: String? = null,
    var filename: String? = null
)
