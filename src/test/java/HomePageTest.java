import constants.URLs;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import pageobject.HomePage;

import static org.junit.Assert.assertTrue;

public class HomePageTest extends BaseTest {
    HomePage homePage = new HomePage(driver);

    @Override
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "D:/WebDriver/bin/chromedriver.exe");
        driver.get(URLs.HOME_PAGE_URL);
        driver.manage().window().maximize();
    }

    @Override
    public void teardown() {
        driver.quit();
    }

    @Test
    @DisplayName("Проверяем переход к разделу \"Булки\"")
    public void checkingThatBunsOfTheConstructorIsSelected() {
        homePage.clickOnTheSaucesButton();
        homePage.clickOnTheBunsButton();
        assertTrue(homePage.bunsOfTheConstructorIsDisplayed());
    }

    @Test
    @DisplayName("Проверяем переход к разделу \"Соусы\"")
    public void checkingThatSaucesOfTheConstructorIsSelected() {
        homePage.clickOnTheSaucesButton();
        assertTrue(homePage.saucesOfTheConstructorIsDisplayed());
    }

    @Test
    @DisplayName("Проверяем переход к разделу \"Начинки\"")
    public void checkingThatFillingsOfTheConstructorIsSelected() {
        homePage.clickOnTheFillingsButton();
        assertTrue(homePage.fillingsOfTheConstructorIsDisplayed());
    }
}
