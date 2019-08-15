import dto.GistRequest
import dto.GistResponse
import io.restassured.RestAssured
import io.restassured.http.Header
import io.restassured.response.ValidatableResponse
import org.apache.http.HttpHeaders
import org.apache.http.HttpStatus.SC_CREATED

const val gistsBaseUrl = "https://api.github.com/gists"

fun createGist(gist: GistRequest, authHeader: String): ValidatableResponse {
    return RestAssured.given()
        .header(Header(HttpHeaders.AUTHORIZATION, authHeader))
        .body(gist)
        .post(gistsBaseUrl)
        .then()
}

fun createGistAndGetId(gistToGet: GistRequest, authHeader: String): String {
    val response = createGist(gistToGet, authHeader).statusCode(SC_CREATED)
    getBody(response, GistResponse::class.java).id?.let {
        return it
    } ?: run {
        throw RuntimeException("Id in the response is null")
    }
}

fun getGist(id: String, authHeader: String): ValidatableResponse {
    return RestAssured.given()
        .header(Header(HttpHeaders.AUTHORIZATION, authHeader))
        .get("$gistsBaseUrl/$id")
        .then()
}

fun updateGist(id: String, gist: GistRequest, authHeader: String): ValidatableResponse {
    return RestAssured.given()
        .header(Header(HttpHeaders.AUTHORIZATION, authHeader))
        .body(gist)
        .patch("$gistsBaseUrl/$id")
        .then()
}

fun deleteGist(id: String, authHeader: String): ValidatableResponse {
    return RestAssured.given()
        .header(Header(HttpHeaders.AUTHORIZATION, authHeader))
        .delete("$gistsBaseUrl/$id")
        .then()
}
