package drivers;

import org.openqa.selenium.firefox.FirefoxDriver;
import properties.Properties;

import java.util.ArrayList;

public class FirefoxWebDriverSingleton extends FirefoxDriver {

    private static FirefoxWebDriverSingleton ourInstance = new FirefoxWebDriverSingleton();
    private final String propertyPath = "src/resources/.property";
    private String url = null;

    public static FirefoxWebDriverSingleton getInstance() {
        return ourInstance;
    }

    public void switchTab(int number) {
        ArrayList<String> tabs = new ArrayList<>(this.getWindowHandles());
        if (number >= 0) {
            this.switchTo().window(tabs.get(number));
        } else {
            this.switchTo().window(tabs.get(tabs.size() + number));
        }
    }

    public void returnToMainPage() {
        Properties properties = Properties.getInstance();
        url = properties.getUrl();
        this.get(url);
        this.navigate().refresh();
    }
}
