package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage {
    WebDriver driver;

    private final By nameField = By.xpath(".//label[text()='Имя']/parent::div/input");
    private final By emailField = By.xpath(".//label[text()='Email']/parent::div/input");
    private final By passwordField = By.xpath(".//label[text()='Пароль']/parent::div/input");
    private final By registrationButton = By.xpath(".//button[text()='Зарегистрироваться']");
    private final By incorrectPassword = By.xpath(".//p[text()='Некорректный пароль']");
    private final By loginButton = By.xpath(".//a[text()='Войти']");

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setName(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    public void setEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public void setPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickRegistrationButton() {
        driver.findElement(registrationButton).click();
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public By getIncorrectPassword() {
        return incorrectPassword;
    }

    public void userRegistration(String name, String email, String password) {
        setName(name);
        setEmail(email);
        setPassword(password);
        clickRegistrationButton();
    }
}
