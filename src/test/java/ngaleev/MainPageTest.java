package ngaleev;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class MainPageTest {

    private WebDriverSingleton driver;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "src/resources/drivers/win64/geckodriver.exe");
        driver = WebDriverSingleton.getInstance();
        driver.get("https://market.yandex.ru/");
    }

    @AfterClass
    public void shutDown(){
        driver.quit();
    }

    @Test
    public void testLogInButton(){
        MainPage page = new MainPage();
        LoginPage loginPage = page.navigateLogin();
        assertTrue(driver.getTitle().equals("Авторизация"));
    }
}
