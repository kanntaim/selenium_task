package ngaleev;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.concurrent.TimeUnit.SECONDS;

public class MainPage {
    private final By loginLocator = By.xpath("//span[contains(text(), \"Войти\")]/../..");
    private final By categoriesLocator = By.xpath("//ul[@class=\"topmenu__list\"]/.//li");
    private final By popularGoodsLocator = By.xpath("//h3[contains(text(), \"Популярные товары\")]");
    private final By lookForThisLocator = By.xpath("//h3[contains(text(), \"Приглядитесь к этим предложениям\")]");
    private final By userLocator = By.className("header2-nav__user");
    private final By userLogOutLocator = By.cssSelector(".user__logout");

    private final String userNameLocatorTemplate = "//span[contains(text(), \"%s\")]";

    private final FirefoxWebDriverSingleton driver = FirefoxWebDriverSingleton.getInstance();

    public MainPage() {
        if (!driver.getTitle().startsWith("Яндекс.Маркет")) {
            throw new IllegalStateException("This is not the main page");
        }
    }

    public LoginPage navigateLogin(){
        List<WebElement> loginButtons  = driver.findElements(loginLocator);
        for(WebElement button: loginButtons){
            if(button.isEnabled() && button.isDisplayed()){
                button.click();
                driver.switchTab(-1);

                return new LoginPage();
            }
        }
        throw new IllegalStateException("Login button not found");
    }

    public CategoryPage navigateRandomCategory(){
        List<WebElement> categoryList = driver.findElements(categoriesLocator);
        Random rand = new Random();
        WebElement btnRandomCategory = categoryList.get(rand.nextInt(categoryList.size()-2));
        btnRandomCategory.click();
        return new CategoryPage();
    }


    public boolean CheckIsAuthorized(String login){
        String userNameLocatorString = String.format(userNameLocatorTemplate, login.substring(1));
        By userNameLocator = By.xpath(userNameLocatorString);
        List<WebElement> userName = driver.findElements(userNameLocator);
        return !userName.isEmpty();
    }

    public List<String> getPopularGoods(String pageSource){
        String popularGoodsFieldRegexp = "Популярные товары<\\/h3>.*<\\/div><\\/div><\\/div><\\/div><\\/div><\\/div><\\/a><\\/div><\\/div>";
        String popularGoodsRegexp = ".*?reactid=\"\\d*\">([A-ZА-Я][^<]*?)<.*?";
        List<String> popularGoods = new LinkedList<>();


        Pattern popularGoodsFieldPattern = Pattern.compile(popularGoodsFieldRegexp);
        Matcher popularGoodsFieldMatcher = popularGoodsFieldPattern.matcher(pageSource);

        if(!popularGoodsFieldMatcher.find()){
            driver.returnToMainPage();
            WebElement lblLookForThis = new WebDriverWait(driver, 10)
                    .until(ExpectedConditions.visibilityOfElementLocated(lookForThisLocator));
            lblLookForThis.click();
            Actions action = new Actions(driver);
            action.sendKeys(Keys.PAGE_DOWN).build().perform();
            new WebDriverWait(driver, 10)
                    .until(ExpectedConditions.visibilityOfElementLocated(popularGoodsLocator));
            return getPopularGoods(driver.getPageSource());
        }
        String popularGoodsField = popularGoodsFieldMatcher.group();


        Pattern popularGoodsPattern = Pattern.compile(popularGoodsRegexp);
        Matcher popularGoodsMatcher = popularGoodsPattern.matcher(popularGoodsField);
        while(popularGoodsMatcher.find()){
            popularGoods.add(popularGoodsMatcher.group(1));
        }
        return popularGoods;
    }

    public void logOut(){
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)     .withTimeout(30, SECONDS)
                .pollingEvery(5, SECONDS)
                .ignoring(NoSuchElementException.class);
        WebElement user = wait.until(ExpectedConditions.elementToBeClickable(userLocator));
        Actions actions = new Actions(driver);
        actions.click(user).build().perform();
        WebElement userLogOut = new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(userLogOutLocator));
        userLogOut.click();
    }

    public boolean CheckIsNotAuthorized() {
        List<WebElement> loginButtons  = driver.findElements(loginLocator);
        for(WebElement button: loginButtons){
            if(button.isEnabled() && button.isDisplayed()){
                return true;
            }
        }
        return false;
    }
}

