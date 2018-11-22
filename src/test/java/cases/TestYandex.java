package cases;

import csv.CsvWriter;
import drivers.FirefoxWebDriverSingleton;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.CategoryPage;
import pages.LoginPage;
import pages.MainPage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class TestYandex {
    private final String propertyPath = System.getProperty("propertyFilePath");
    private final String authorizeTitle = "Авторизация";

    private String webdriverPath;
    private String url;
    private FirefoxWebDriverSingleton driver;

    @BeforeTest
    public void setUp() {
        assertTrue(setProperties());
        System.setProperty("webdriver.gecko.driver", webdriverPath);
        driver = FirefoxWebDriverSingleton.getInstance();
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @AfterTest
    public void shutDown() {
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

    private boolean setProperties() {
        try (BufferedReader propertyReader = new BufferedReader(new FileReader(propertyPath))) {
            url = propertyReader.readLine();
            webdriverPath = propertyReader.readLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
