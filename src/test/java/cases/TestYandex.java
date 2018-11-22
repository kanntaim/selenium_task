package cases;

import framework.utils.CsvWriter;
import framework.utils.Properties;
import framework.drivers.GeneralWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.CategoryPage;
import pages.LoginPage;
import pages.MainPage;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class TestYandex {
    private final String authorizeTitle = "Авторизация";

    private Properties properties = Properties.getInstance();
    private String url = properties.getUrl();
    private GeneralWebDriver driver;

    @BeforeTest
    public void setUp() {
        System.setProperty(properties.getWebdriverName(), properties.getWebdriverPath());
        driver = GeneralWebDriver.getInstance();
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }


    @Parameters({"login", "password"})
    @Test
    public void testCasePopularGoods(String login, String password) {
        MainPage mainPage = new MainPage();
        LoginPage loginPage = mainPage.navigateLogin();
        assertEquals(driver.getTitle(), authorizeTitle);

        loginPage.authorize(login, password);
        driver.returnToMainPage();
        assertTrue(mainPage.isAuthorized(login));

        mainPage = new MainPage();
        CategoryPage categoryPage = mainPage.navigateRandomCategory();
        assertTrue(categoryPage.compareCategoryNames());

        driver.returnToMainPage();
        String pageSource = driver.getPageSource();
        List<String> test = mainPage.getPopularGoods(pageSource);
        assertTrue(CsvWriter.createFile(test));

        driver.returnToMainPage();
        mainPage.logOut();
        assertTrue(mainPage.CheckIsNotAuthorized());
    }

}
