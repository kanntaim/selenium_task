package ngaleev;

import org.openqa.selenium.Keys;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

public class WebDriverSingleton extends FirefoxDriver {
    private static WebDriverSingleton ourInstance = new WebDriverSingleton();

    public static WebDriverSingleton getInstance() {
        return ourInstance;
    }

    private WebDriverSingleton() {
        this.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);
    }

    public void closeActiveTab(){
        Actions closeTab = new Actions(this);
        closeTab.keyDown(Keys.LEFT_CONTROL).keyDown("w").keyUp(Keys.LEFT_CONTROL).keyUp("w").perform();
        this.switchTo().window(this.getWindowHandle());
    }

}
