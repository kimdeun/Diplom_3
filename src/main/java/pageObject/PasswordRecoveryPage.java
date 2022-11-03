package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PasswordRecoveryPage {
    private final By loginButton = By.xpath(".//a[text()='Войти']");
    private WebDriver driver;

    public PasswordRecoveryPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickToTheLoginButton() {
        driver.findElement(loginButton).click();
    }
}
