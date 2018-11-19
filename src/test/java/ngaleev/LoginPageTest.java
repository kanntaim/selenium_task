package ngaleev;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

public class LoginPageTest {
    private WebDriverSingleton driver;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "src/resources/drivers/win64/geckodriver.exe");
        driver = WebDriverSingleton.getInstance();
        driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);
        driver.get("https://market.yandex.ru/");
    }

    @AfterClass
    public void shutDown(){
        driver.quit();
    }

    @Test
    public void testAutorise(){
        MainPage mainPage = new MainPage();
        LoginPage loginPage = mainPage.navigateLogin();
        loginPage = loginPage.inputLogin("r2d2.and.c3po");
        loginPage = loginPage.inputPassword("25672478r2d2");
        assertTrue(driver.getTitle().equals("Авторизация"));//FIXME
    }

}
