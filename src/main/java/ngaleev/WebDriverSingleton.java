package ngaleev;

import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class WebDriverSingleton extends FirefoxDriver {
    private static WebDriverSingleton ourInstance = new WebDriverSingleton();

    public static WebDriverSingleton getInstance() {
        return ourInstance;
    }

    private WebDriverSingleton() {
        this.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);
    }

}
