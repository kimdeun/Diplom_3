package TestsInYandexBrowser;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.PersonalAccountPage;
import pageObject.RegistrationPage;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class PersonalAccountPageTest extends BaseTest {
    RegistrationPage registrationPage = new RegistrationPage(driver);
    LoginPage loginPage = new LoginPage(driver);
    HomePage homePage = new HomePage(driver);
    PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);
    private Random random = new Random();
    String name = "userName" + random.nextInt(30000);
    String email = "userEmail" + random.nextInt(30000) + "@gmail.com";
    String password = "userPassword" + random.nextInt(30000);

    @Override
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "D:/WebDriver/bin/yandexdriver.exe");
        driver.get("https://stellarburgers.nomoreparties.site/register");
        driver.manage().window().maximize();

        registrationPage.userRegistration(name, email, password);
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.urlToBe("https://stellarburgers.nomoreparties.site/login"));

        loginPage.userLogin(email, password);
        wait.until(ExpectedConditions.urlToBe("https://stellarburgers.nomoreparties.site/"));
    }

    @Test
    @DisplayName("Проверяем переход в личный кабинет с главной страницы")
    public void checkingTheLinkToThePersonalAccount() {
        homePage.clickOnTheButtonToPersonalAccount();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.urlToBe("https://stellarburgers.nomoreparties.site/account/profile"));
        assertEquals("https://stellarburgers.nomoreparties.site/account/profile", personalAccountPage.getLink());
    }

    @Test
    @DisplayName("Проверяем выход из аккаунта")
    public void personalAccountExit() {
        homePage.clickOnTheButtonToPersonalAccount();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.urlToBe("https://stellarburgers.nomoreparties.site/account/profile"));
        personalAccountPage.clickExitButton();
        wait.until(ExpectedConditions.urlToBe("https://stellarburgers.nomoreparties.site/login"));
        assertEquals("https://stellarburgers.nomoreparties.site/login", loginPage.getLink());
    }

    @Test
    @DisplayName("Проверяем переход в конструктор через кнопку \"Конструктор\"")
    public void checkingLinkToTheConstructorByConstructorButton() {
        homePage.clickOnTheButtonToPersonalAccount();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.urlToBe("https://stellarburgers.nomoreparties.site/account/profile"));
        personalAccountPage.clickConstructorButton();
        assertEquals("https://stellarburgers.nomoreparties.site/", personalAccountPage.getLink());
    }

    @Test
    @DisplayName("Проверяем переход в конструктор через нажатие по лого")
    public void checkingLinkToTheConstructorByLogoButton() {
        homePage.clickOnTheButtonToPersonalAccount();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.urlToBe("https://stellarburgers.nomoreparties.site/account/profile"));
        personalAccountPage.clickLogo();
        assertEquals("https://stellarburgers.nomoreparties.site/", personalAccountPage.getLink());
    }
}
