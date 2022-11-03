package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PersonalAccountPage {
    WebDriver driver;

    private final By exitButton = By.xpath(".//button[text()='Выход']");
    public PersonalAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getLink() {
        return driver.getCurrentUrl();
    }

    public void clickExitButton() {
        driver.findElement(exitButton).click();
    }
}
