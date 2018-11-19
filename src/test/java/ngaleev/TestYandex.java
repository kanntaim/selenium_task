package ngaleev;

import org.testng.annotations.*;

import static org.testng.Assert.assertTrue;

public class TestYandex {

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
    public void testCasePopularGoods(){
        MainPage mainPage = new MainPage();
        LoginPage loginPage = mainPage.navigateLogin();
        assertTrue(driver.getTitle().equals("Авторизация"));

        loginPage = loginPage.authorize("r2d2.and.c3po", "25672478r2d2");
        driver.switchTab(0);
        assertTrue(driver.getTitle().startsWith("Яндекс"));//FIXME
    }

}
