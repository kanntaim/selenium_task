package ngaleev;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class MainPage {
    private final By loginLocator = By.xpath("//span[contains(text(), \"Войти\")]/../..");

    private final WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;

        if (!driver.getTitle().startsWith("Яндекс.Маркет")) {
            throw new IllegalStateException("This is not the main page");
        }
    }

    public WebDriver navigateLogin(){
        List<WebElement> loginButtons  = driver.findElements(loginLocator);
        for(WebElement button: loginButtons){
            if(button.isEnabled() && button.isDisplayed()){
                button.click();
                ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
                driver.switchTo().window(tabs.get(tabs.size()-1));
            }
        }
        return driver;
    }
}

