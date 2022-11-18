import api.auth.AuthClient;
import constants.URLs;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.Test;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobject.LoginPage;
import pageobject.RegistrationPage;

import java.util.Random;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class RegistrationPageTest extends BaseTest {
    AuthClient authClient = new AuthClient();
    RegistrationPage registrationPage = new RegistrationPage(driver);
    LoginPage loginPage = new LoginPage(driver);
    private Random random = new Random();
    String wrongPassword = "p" + random.nextInt(100);

    @Override
    public void setUp() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";

        System.setProperty("webdriver.chrome.driver", "D:/WebDriver/bin/chromedriver.exe");
        driver.get(URLs.REGISTER_PAGE_URL);
        driver.manage().window().maximize();
    }

    @Test
    @DisplayName("Проверяем, что можно зарегистрироваться с паролем из 6 и более символов")
    public void userRegistrationWithPasswordWithMoreThenSixCharacters() {
        registrationPage.userRegistration(name, email, password);
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.urlToBe(URLs.LOGIN_PAGE_URL));

        response = authClient.authorizationNewUser(email, password);
        bearerToken = response.extract().body().path("accessToken");
        tokenForRequest = bearerToken.substring(7);

        assertTrue(driver.findElement(loginPage.getLoginButton()).isDisplayed());
    }

    @Test
    @DisplayName("Проверяем, что нельзя зарегистрироваться с паролем меньше, чем 6 символов")
    public void userRegistrationWithPasswordWithLessThenSixCharacters() {
        try {
            registrationPage.userRegistration(name, email, wrongPassword);
            boolean incorrectPasswordIsDisplayed = driver.findElement(registrationPage.getIncorrectPassword()).isDisplayed();
            assertTrue(incorrectPasswordIsDisplayed);
        } catch (NoSuchElementException exception) {
            response = authClient.authorizationNewUser(email, password);
            bearerToken = response.extract().body().path("accessToken");
            tokenForRequest = bearerToken.substring(7);
            fail();
        }
    }
}
