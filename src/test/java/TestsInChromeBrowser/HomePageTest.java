package TestsInChromeBrowser;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import pageObject.HomePage;

import static org.junit.Assert.assertTrue;

public class HomePageTest extends BaseTest {
    HomePage homePage = new HomePage(driver);

    @Test
    @DisplayName("Проверяем переход к разделу \"Булки\"")
    public void checkingThatBunsOfTheConstructorIsSelected() {
        homePage.clickOnTheSaucesButton();
        homePage.clickOnTheBunsButton();
        assertTrue(driver.findElement(homePage.getBunsOfTheConstructorIsSelected()).isDisplayed());
    }

    @Test
    @DisplayName("Проверяем переход к разделу \"Соусы\"")
    public void checkingThatSaucesOfTheConstructorIsSelected() {
        homePage.clickOnTheSaucesButton();
        assertTrue(driver.findElement(homePage.getSaucesOfTheConstructorIsSelected()).isDisplayed());
    }

    @Test
    @DisplayName("Проверяем переход к разделу \"Начинки\"")
    public void checkingThatFillingsOfTheConstructorIsSelected() {
        homePage.clickOnTheFillingsButton();
        assertTrue(driver.findElement(homePage.getFillingsOfTheConstructorIsSelected()).isDisplayed());
    }
}
