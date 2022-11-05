package TestsInYandexBrowser;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseTest {
    public WebDriver driver = new ChromeDriver();

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "D:/WebDriver/bin/yandexdriver.exe");
        driver.get("https://stellarburgers.nomoreparties.site/");
        driver.manage().window().maximize();
    }

    @After
    public void teardown() {
        driver.quit();
    }

}
