import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class ReqresInTest {

    @Test
    void singleUserPositiveTest() {

        given()
                .log().uri()
                .contentType(JSON)
                .when()
                .get("https://reqres.in/api/users/2")
                .then()
                .log().status()
                .log().body()
                .assertThat()
                .statusCode(200)
                .body("data.id", is(2))
                .body("data.first_name", is("Janet"))
                .body("data.last_name", is("Weaver"))
                .body(matchesJsonSchemaInClasspath("schemes/single-user-responce.json"));
    }


    @Test
    void createUserPositiveTest() {

        String body = "{ \"name\": \"Dima\", \"job\": \"QA\" }";

        given()
                .log().uri()
                .body(body)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().status()
                .log().body()
                .assertThat()
                .statusCode(201)
                .body("name", is("Dima"))
                .body("job", is("QA"));
    }

    @Test
    void updateUserPositiveTest() {

        String body = "{ \"name\": \"Ivan\", \"job\": \"Developer\" }";

        given()
                .log().uri()
                .body(body)
                .contentType(JSON)
                .when()
                .put("https://reqres.in/api/users/2")
                .then()
                .log().status()
                .log().body()
                .assertThat()
                .statusCode(200)
                .body("name", is("Ivan"))
                .body("job", is("Developer"));
    }


    @Test
    void deleteUserPositiveTest() {


        given()
                .log().uri()
                .contentType(JSON)
                .when()
                .delete("https://reqres.in/api/users/2")
                .then()
                .log().status()
                .log().body()
                .assertThat()
                .statusCode(204)
                .body(is(""));
    }

    @Test
    void unsuccessfulLoginTest() {
        String body = "{ \"email\": \"emma.wong@reqres.in\" }";

        given()
                .log().uri()
                .body(body)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing password"));
    }
}
