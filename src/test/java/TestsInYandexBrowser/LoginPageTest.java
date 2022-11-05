package TestsInYandexBrowser;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.PasswordRecoveryPage;
import pageObject.RegistrationPage;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class LoginPageTest extends BaseTest {
    HomePage homePage = new HomePage(driver);
    LoginPage loginPage = new LoginPage(driver);
    PasswordRecoveryPage passwordRecoveryPage = new PasswordRecoveryPage(driver);
    RegistrationPage registrationPage = new RegistrationPage(driver);

    private Random random = new Random();
    String name = "userName" + random.nextInt(30000);
    String email = "userEmail" + random.nextInt(30000) + "@gmail.com";
    String password = "userPassword" + random.nextInt(30000);

    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "D:/WebDriver/bin/yandexdriver.exe");
        driver.get("https://stellarburgers.nomoreparties.site/register");
        driver.manage().window().maximize();

        registrationPage.userRegistration(name, email, password);
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.urlToBe("https://stellarburgers.nomoreparties.site/login"));
    }

    @Test
    @DisplayName("Проверяем, что можно авторизоваться с начальной страницы с помощью кнопки \"Войти в аккаунт\"")
    public void userLoginFromTheHomePageByLoginButton() {
        loginPage.clickLogo();
        homePage.clickOnTheAccountLoginButton();
        loginPage.userLogin(email, password);
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.urlToBe("https://stellarburgers.nomoreparties.site/"));
        assertEquals("https://stellarburgers.nomoreparties.site/", homePage.getLink());
    }

    @Test
    @DisplayName("Проверяем, что можно авторизоваться с начальной страницы с помощью кнопки \"Личный кабинет\"")
    public void userLoginFromTheHomePageByPersonalAccountButton() {
        loginPage.clickLogo();
        homePage.clickOnTheButtonToPersonalAccount();
        loginPage.userLogin(email, password);
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.urlToBe("https://stellarburgers.nomoreparties.site/"));
        assertEquals("https://stellarburgers.nomoreparties.site/", homePage.getLink());
    }

    @Test
    @DisplayName("Проверяем, что можно авторизоваться со страницы регистрации с помощью кнопки \"Войти\"")
    public void userLoginFromTheRegistrationPage() {
        loginPage.clickToTheRegistrationButton();
        registrationPage.clickLoginButton();
        loginPage.userLogin(email, password);
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.urlToBe("https://stellarburgers.nomoreparties.site/"));
        assertEquals("https://stellarburgers.nomoreparties.site/", homePage.getLink());
    }

    @Test
    @DisplayName("Проверяем, что можно авторизоваться со страницы восстановления пароля с помощью кнопки \"Войти\"")
    public void userLoginFromTheRecoveryPage() {
        loginPage.clickToThePasswordRecoveryButton();
        passwordRecoveryPage.clickToTheLoginButton();
        loginPage.userLogin(email, password);
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.urlToBe("https://stellarburgers.nomoreparties.site/"));
        assertEquals("https://stellarburgers.nomoreparties.site/", homePage.getLink());
    }
}
