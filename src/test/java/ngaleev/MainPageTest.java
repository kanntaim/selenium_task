package ngaleev;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class MainPageTest {

    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "/home/user/bin");
        driver = new FirefoxDriver();
        driver.get("market.yandex.ru");
    }

    @Test
    public void testLogInButton(){
        MainPage page = new MainPage(driver);
        LoginPage newPage = page.submitLogin();
        assertEquals(driver.getTitle(),"Aвторизация");
    }
}
