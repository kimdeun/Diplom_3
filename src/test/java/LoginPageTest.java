import constants.URLs;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobject.HomePage;
import pageobject.LoginPage;
import pageobject.PasswordRecoveryPage;
import pageobject.RegistrationPage;

import static org.junit.Assert.assertEquals;

public class LoginPageTest extends BaseTest {
    HomePage homePage = new HomePage(driver);
    LoginPage loginPage = new LoginPage(driver);
    PasswordRecoveryPage passwordRecoveryPage = new PasswordRecoveryPage(driver);
    RegistrationPage registrationPage = new RegistrationPage(driver);

    @Test
    @DisplayName("Проверяем, что можно авторизоваться с начальной страницы с помощью кнопки \"Войти в аккаунт\"")
    public void userLoginFromTheHomePageByLoginButton() {
        loginPage.clickLogo();
        homePage.clickOnTheAccountLoginButton();
        loginPage.userLogin(email, password);
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.urlToBe(URLs.HOME_PAGE_URL));
        assertEquals(URLs.HOME_PAGE_URL, homePage.getLink());
    }

    @Test
    @DisplayName("Проверяем, что можно авторизоваться с начальной страницы с помощью кнопки \"Личный кабинет\"")
    public void userLoginFromTheHomePageByPersonalAccountButton() {
        loginPage.clickLogo();
        homePage.clickOnTheButtonToPersonalAccount();
        loginPage.userLogin(email, password);
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.urlToBe(URLs.HOME_PAGE_URL));
        assertEquals(URLs.HOME_PAGE_URL, homePage.getLink());
    }

    @Test
    @DisplayName("Проверяем, что можно авторизоваться со страницы регистрации с помощью кнопки \"Войти\"")
    public void userLoginFromTheRegistrationPage() {
        loginPage.clickToTheRegistrationButton();
        registrationPage.clickLoginButton();
        loginPage.userLogin(email, password);
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.urlToBe(URLs.HOME_PAGE_URL));
        assertEquals(URLs.HOME_PAGE_URL, homePage.getLink());
    }

    @Test
    @DisplayName("Проверяем, что можно авторизоваться со страницы восстановления пароля с помощью кнопки \"Войти\"")
    public void userLoginFromTheRecoveryPage() {
        loginPage.clickToThePasswordRecoveryButton();
        passwordRecoveryPage.clickToTheLoginButton();
        loginPage.userLogin(email, password);
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.urlToBe(URLs.HOME_PAGE_URL));
        assertEquals(URLs.HOME_PAGE_URL, homePage.getLink());
    }
}
