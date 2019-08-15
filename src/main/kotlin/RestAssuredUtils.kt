import io.restassured.response.ValidatableResponse

fun <T> getBody(response: ValidatableResponse, cls: Class<T>): T {
    return response.extract().body().`as`(cls)
}

fun assertStatusCode(response: ValidatableResponse, expectedStatusCode: Int) {
    response.assertThat().statusCode(expectedStatusCode)
}
