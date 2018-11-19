package ngaleev;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class WebDriverSingleton extends FirefoxDriver {
    private static WebDriverSingleton ourInstance = new WebDriverSingleton();

    public static WebDriverSingleton getInstance() {
        return ourInstance;
    }

    private WebDriverSingleton() {
        this.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);
    }

    public void switchTab(int number){
        ArrayList<String> tabs = new ArrayList<>(this.getWindowHandles());
        if (number>=0){
            this.switchTo().window(tabs.get(number));
        }else{
            this.switchTo().window(tabs.get(tabs.size()+number));
        }
    }

}
