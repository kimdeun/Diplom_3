import api.auth.AuthClient;
import constants.URLs;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static io.restassured.RestAssured.given;

public class BaseTest {
    public WebDriver driver = new ChromeDriver();

    String email = RandomStringUtils.randomAlphanumeric(7) + "@gmail.com";
    String password = RandomStringUtils.randomAlphabetic(7);
    String name = RandomStringUtils.randomAlphabetic(7);

    ValidatableResponse response;
    String bearerToken;
    String tokenForRequest;

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";

        AuthClient authClient = new AuthClient();

        response = authClient.registeringNewUser(email, password, name);
        bearerToken = response.extract().body().path("accessToken");
        tokenForRequest = bearerToken.substring(7);

        System.setProperty("webdriver.chrome.driver", "D:/WebDriver/bin/chromedriver.exe");
//        System.setProperty("webdriver.chrome.driver", "D:/WebDriver/bin/yandexdriver.exe");

        driver.get(URLs.LOGIN_PAGE_URL);
        driver.manage().window().maximize();
    }

    @After
    public void teardown() {
        driver.quit();

        if (tokenForRequest != null) {
            given()
                    .header("Authorization", "Bearer " + tokenForRequest)
                    .when()
                    .delete("api/auth/user")
                    .then()
                    .statusCode(202);
        }
    }

}
