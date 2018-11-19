package ngaleev;

import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.*;

import static org.testng.Assert.assertTrue;

public class TestCasePopularItems {

    private WebDriverSingleton driver;

    @BeforeTest
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "src/resources/drivers/win64/geckodriver.exe");
        driver = WebDriverSingleton.getInstance();
        driver.get("https://market.yandex.ru/");
    }

    @AfterTest
    public void shutDown(){
        driver.quit();
    }

    @Test
    public void testLogInButton(){
        MainPage mainPage = new MainPage();
        LoginPage loginPage = mainPage.navigateLogin();
        assertTrue(driver.getTitle().equals("Авторизация"));
        loginPage = loginPage.authorize("r2d2.and.c3po", "25672478r2d2");
        Actions closeTab = new Actions(driver);
        closeTab.keyDown(Keys.LEFT_CONTROL).keyDown("w").keyUp(Keys.LEFT_CONTROL).keyUp("w").perform();
        driver.switchTo().window(driver.getWindowHandle());
        assertTrue(driver.getTitle().startsWith("Яндекс"));//FIXME
    }

}
