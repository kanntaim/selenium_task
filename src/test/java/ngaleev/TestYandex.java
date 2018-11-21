package ngaleev;

import org.testng.annotations.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

public class TestYandex {

    private WebDriverSingleton driver;

    @BeforeTest
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "src/resources/drivers/win32/geckodriver.exe");
        driver = WebDriverSingleton.getInstance();
        driver.get("https://market.yandex.ru/");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
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
        driver.returnToMainPage();
        assertTrue(mainPage.CheckIsAuthorized("r2d2.and.c3po"));

        mainPage = new MainPage();
//        CategoryPage categoryPage = mainPage.navigateRandomCategory();
//        assertTrue(categoryPage.compareCategoryNames());
//
//        driver.returnToMainPage();
//        String pageSource = driver.getPageSource();
//        List<String> test = mainPage.getPopularGoods(pageSource);
//        assertTrue(CsvWriter.createFile(test));

        mainPage.logOut();
        assertTrue(mainPage.CheckIsNotAuthorized());
    }

}
