package ngaleev;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class MainPageTest {

    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "src/resources/drivers/win64/geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);
        driver.get("https://market.yandex.ru/");
    }

    @AfterClass
    public void shutDown(){
        driver.quit();
    }

    @Test
    public void testLogInButton(){
        MainPage page = new MainPage(driver);
        LoginPage loginPage = page.navigateLogin();
        assertTrue(driver.getTitle().equals("Авторизация"));
    }
}
