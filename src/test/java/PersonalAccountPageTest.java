import api.auth.AuthClient;
import constants.URLs;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobject.HomePage;
import pageobject.LoginPage;
import pageobject.PersonalAccountPage;

import static org.junit.Assert.assertEquals;

public class PersonalAccountPageTest extends BaseTest {
    LoginPage loginPage = new LoginPage(driver);
    HomePage homePage = new HomePage(driver);
    PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);

    @Override
    public void setUp() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";

        AuthClient authClient = new AuthClient();

        response = authClient.registeringNewUser(email, password, name);
        bearerToken = response.extract().body().path("accessToken");
        tokenForRequest = bearerToken.substring(7);

        System.setProperty("webdriver.chrome.driver", "D:/WebDriver/bin/chromedriver.exe");
        driver.get(URLs.LOGIN_PAGE_URL);
        driver.manage().window().maximize();
        loginPage.userLogin(email, password);
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.urlToBe(URLs.HOME_PAGE_URL));
    }

    @Test
    @DisplayName("Проверяем переход в личный кабинет с главной страницы")
    public void checkingTheLinkToThePersonalAccount() {
        homePage.clickOnTheButtonToPersonalAccount();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.urlToBe(URLs.PERSONAL_ACCOUNT_PAGE_URL));
        assertEquals(URLs.PERSONAL_ACCOUNT_PAGE_URL, personalAccountPage.getLink());
    }

    @Test
    @DisplayName("Проверяем выход из аккаунта")
    public void personalAccountExit() {
        homePage.clickOnTheButtonToPersonalAccount();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.urlToBe(URLs.PERSONAL_ACCOUNT_PAGE_URL));
        personalAccountPage.clickExitButton();
        wait.until(ExpectedConditions.urlToBe(URLs.LOGIN_PAGE_URL));
        assertEquals(URLs.LOGIN_PAGE_URL, loginPage.getLink());
    }

    @Test
    @DisplayName("Проверяем переход в конструктор через кнопку \"Конструктор\"")
    public void checkingLinkToTheConstructorByConstructorButton() {
        homePage.clickOnTheButtonToPersonalAccount();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.urlToBe(URLs.PERSONAL_ACCOUNT_PAGE_URL));
        personalAccountPage.clickConstructorButton();
        assertEquals(URLs.HOME_PAGE_URL, personalAccountPage.getLink());
    }

    @Test
    @DisplayName("Проверяем переход в конструктор через нажатие по лого")
    public void checkingLinkToTheConstructorByLogoButton() {
        homePage.clickOnTheButtonToPersonalAccount();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.urlToBe(URLs.PERSONAL_ACCOUNT_PAGE_URL));
        personalAccountPage.clickLogo();
        assertEquals(URLs.HOME_PAGE_URL, personalAccountPage.getLink());
    }
}
