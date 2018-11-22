package framework.drivers;

import framework.utils.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.ArrayList;
import java.util.List;

public class FirefoxWebDriverSingleton {

    private static FirefoxWebDriverSingleton ourInstance = new FirefoxWebDriverSingleton();
    private WebDriver driver;

    public static FirefoxWebDriverSingleton getInstance() {
        return ourInstance;
    }

    private FirefoxWebDriverSingleton(){
        Properties properties = Properties.getInstance();
        String browserName = properties.getBrowserName();
        switch (browserName){
            case "Firefox":
                driver = new FirefoxDriver();
                break;
            case "Chrome":
                driver = new ChromeDriver();
        }
    }

    public void switchTab(int number) {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        if (number >= 0) {
            driver.switchTo().window(tabs.get(number));
        } else {
            driver.switchTo().window(tabs.get(tabs.size() + number));
        }
    }

    public void returnToMainPage() {
        Properties properties = Properties.getInstance();
        String url = properties.getUrl();
        this.switchTab(0);
        driver.get(url);
        driver.navigate().refresh();
    }

    public String getTitle(){
        return driver.getTitle();
    }

    public void quit(){
        driver.quit();
    }

    public String getPageSource(){
        return driver.getPageSource();
    }

    public void get(String url){
        driver.get(url);
    }

    public WebDriver.Options manage(){
        return driver.manage();
    }

    public List<WebElement> findElements(By by){
        return driver.findElements(by);
    }

    public WebElement findElement(By by){
        return driver.findElement(by);
    }

    public WebDriver getDriver(){
        return driver;
    }
}
