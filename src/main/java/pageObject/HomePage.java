package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private WebDriver driver;

    private final By linkToThePersonalAccount = By.xpath(".//nav/a[@href='/account']");
    private final By accountLoginButton = By.xpath(".//button[text()='Войти в аккаунт']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public String getLink() {
        return driver.getCurrentUrl();
    }

    public void clickOnTheButtonToPersonalAccount() {
        driver.findElement(linkToThePersonalAccount).click();
    }

    public void clickOnTheAccountLoginButton() {
        driver.findElement(accountLoginButton).click();
    }
}
