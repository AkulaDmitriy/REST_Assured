package tests;

import io.qameta.allure.restassured.AllureRestAssured;
import models.lombok.CreateUserLombokModel;
import models.lombok.CreateUserLombokModelResponse;
import models.pojo.CreateUserPojoModel;
import models.pojo.CreateUserPojoModelResponse;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.hamcrest.Matchers.is;

public class ExtendedTest {


    @Test
    void createUserWithPojoPositiveTest() {


        CreateUserPojoModel createUserPojoModel = new CreateUserPojoModel();
        createUserPojoModel.setName("Dima");
        createUserPojoModel.setJob("QA");

        CreateUserPojoModelResponse response = given()
                .log().uri()
                .body(createUserPojoModel)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().status()
                .log().body()
                .assertThat()
                .statusCode(201)
                .extract().as(CreateUserPojoModelResponse.class);

        assertThat(response.getJob()).isEqualTo("QA");
        assertThat(response.getName()).isEqualTo("Dima");



    }
    @Test
    void createUserWithLombokPositiveTest() {


        CreateUserLombokModel createUserLombokModel = new CreateUserLombokModel();
        createUserLombokModel.setName("Dima");
        createUserLombokModel.setJob("QA");

        CreateUserLombokModelResponse response = given()
                .log().uri()
                .body(createUserLombokModel)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().status()
                .log().body()
                .assertThat()
                .statusCode(201)
                .extract().as(CreateUserLombokModelResponse.class);

        assertThat(response.getJob()).isEqualTo("QA");
        assertThat(response.getName()).isEqualTo("Dima");



    }

    @Test
    void createUserWithAllurePositiveTest() {


        CreateUserLombokModel createUserLombokModel = new CreateUserLombokModel();
        createUserLombokModel.setName("Dima");
        createUserLombokModel.setJob("QA");

        CreateUserLombokModelResponse response = given()
                .filter(new AllureRestAssured())
                .log().uri()
                .body(createUserLombokModel)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().status()
                .log().body()
                .assertThat()
                .statusCode(201)
                .extract().as(CreateUserLombokModelResponse.class);

        assertThat(response.getJob()).isEqualTo("QA");
        assertThat(response.getName()).isEqualTo("Dima");



    }
}