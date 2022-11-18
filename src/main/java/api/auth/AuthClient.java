package api.auth;

import io.restassured.response.ValidatableResponse;
import model.User;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;

public class AuthClient {

    public ValidatableResponse registeringNewUser(String email, String password, String name) {
        User user = new User(email, password, name);
        return given()
                .log().all()
                .header("Content-type", "application/json")
                .body(user)
                .when()
                .post("/api/auth/register")
                .then().log().all()
                .assertThat()
                .body("accessToken", notNullValue());
    }

    public ValidatableResponse authorizationNewUser(String email, String password) {
        User user = new User(email, password);
        return given()
                .log().all()
                .header("Content-type", "application/json")
                .body(user)
                .post("/api/auth/login")
                .then().log().all()
                .assertThat()
                .body("accessToken", notNullValue());
    }
}
