import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObject.LoginPage;
import pageObject.RegistrationPage;

import java.util.Random;

import static org.junit.Assert.assertTrue;

public class RegistrationPageTest extends BaseTest {
    RegistrationPage registrationPage = new RegistrationPage(driver);
    LoginPage loginPage = new LoginPage(driver);
    private Random random = new Random();
    String name = "userName" + random.nextInt(30000);
    String email = "userEmail" + random.nextInt(30000) + "@gmail.com";
    String password = "userPassword" + random.nextInt(30000);
    String wrongPassword = "p" + random.nextInt(100);

    @Override
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "D:/WebDriver/bin/chromedriver.exe");
        driver.get("https://stellarburgers.nomoreparties.site/register");
        driver.manage().window().maximize();
    }

    @Test
    @DisplayName("Проверяем, что можно зарегистрироваться с паролем из 6 и более символов")
    public void userRegistrationWithPasswordWithMoreThenSixCharacters() {
        registrationPage.userRegistration(name, email, password);
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.urlToBe("https://stellarburgers.nomoreparties.site/login"));
        assertTrue(driver.findElement(loginPage.getLoginButton()).isDisplayed());
    }

    @Test
    @DisplayName("Проверяем, что можно зарегистрироваться с паролем меньше, чем 6 символов")
    public void userRegistrationWithPasswordWithLessThenSixCharacters() {
        registrationPage.userRegistration(name, email, wrongPassword);
        assertTrue(driver.findElement(registrationPage.getIncorrectPassword()).isDisplayed());
    }
}
