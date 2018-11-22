package pages;

import framework.drivers.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainPage {
    private final By loginLocator = By.xpath("//span[contains(text(), \"Войти\")]/ancestor::a[@role=\"button\"]");
    private final By categoriesLocator = By.xpath("//ul[@class=\"topmenu__list\"]/.//li");
    private final By userLocator = By.className("n-passport-suggest-popup-opener");
    private final By popularGoodsLocator = By.xpath("//h3[contains(text(), \"Популярные товары\")]");
    private final By userLogOutLocator = By.cssSelector(".user__logout");
    private final String userNameLocatorTemplate = "//span[contains(text(), \"%s\")]";
    private final WebDriver driver = WebDriver.getInstance();

    public MainPage() {
        if (!driver.getTitle().startsWith("Яндекс.Маркет")) {
            throw new IllegalStateException("This is not the main page");
        }
    }

    public LoginPage navigateLogin() {
        List<WebElement> loginButtons = driver.findElements(loginLocator);
        for (WebElement button : loginButtons) {
            if (button.isEnabled() && button.isDisplayed()) {
                button.click();
                driver.switchTab(-1);

                return new LoginPage();
            }
        }
        throw new IllegalStateException("Login button not found");
    }

    public CategoryPage navigateRandomCategory() {
        List<WebElement> categoryList = driver.findElements(categoriesLocator);
        Random rand = new Random();
        WebElement btnRandomCategory = categoryList.get(rand.nextInt(categoryList.size() - 2));
        btnRandomCategory.click();
        return new CategoryPage();
    }


    public boolean isAuthorized(String login) {
        String userNameLocatorString = String.format(userNameLocatorTemplate, login.substring(1));
        By userNameLocator = By.xpath(userNameLocatorString);
        List<WebElement> userName = driver.findElements(userNameLocator);
        return !userName.isEmpty();
    }

    public List<String> getPopularGoods(String pageSource) {
        String popularGoodsFieldRegexp = "Популярные товары</h3>.*</div></div></div></div></div></div></a></div></div>";
        String popularGoodsRegexp = ".*?reactid=\"\\d*\">([A-ZА-Я][^<]*?)<.*?";
        List<String> popularGoods = new LinkedList<>();


        Pattern popularGoodsFieldPattern = Pattern.compile(popularGoodsFieldRegexp);
        Matcher popularGoodsFieldMatcher = popularGoodsFieldPattern.matcher(pageSource);

        if (!popularGoodsFieldMatcher.find()) {
            driver.returnToMainPage();
            ((JavascriptExecutor) driver.getDriver()).executeScript("window.scrollTo(0,500);");
            new WebDriverWait(driver.getDriver(), 10)
                    .until(ExpectedConditions.visibilityOfElementLocated(popularGoodsLocator));
            return getPopularGoods(driver.getPageSource());
        }
        String popularGoodsField = popularGoodsFieldMatcher.group();


        Pattern popularGoodsPattern = Pattern.compile(popularGoodsRegexp);
        Matcher popularGoodsMatcher = popularGoodsPattern.matcher(popularGoodsField);
        while (popularGoodsMatcher.find()) {
            popularGoods.add(popularGoodsMatcher.group(1));
        }
        return popularGoods;
    }

    public void logOut() {
        Wait<org.openqa.selenium.WebDriver> wait = new FluentWait<>(driver.getDriver()).withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofMillis(600))
                .ignoring(NoSuchElementException.class);
        WebElement user = wait.until(ExpectedConditions.elementToBeClickable(userLocator));
        Actions actions = new Actions(driver.getDriver());
        actions.click(user).build().perform();
        WebElement userLogOut = new WebDriverWait(driver.getDriver(), 5)
                .until(ExpectedConditions.elementToBeClickable(userLogOutLocator));
        userLogOut.click();
    }

    public boolean CheckIsNotAuthorized() {
        List<WebElement> loginButtons = driver.findElements(loginLocator);
        for (WebElement button : loginButtons) {
            if (button.isEnabled() && button.isDisplayed()) {
                return true;
            }
        }
        return false;
    }
}

