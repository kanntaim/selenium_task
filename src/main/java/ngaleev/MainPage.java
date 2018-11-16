package ngaleev;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage {
    By loginLocator = By.xpath("//span[contains(text(), \"Войти\")]/../..");

    private final WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;

        if (!driver.getTitle().startsWith("Яндекс.Маркет")) {
            throw new IllegalStateException("This is not the main page");
        }
    }

    public WebDriver submitLogin(){
        WebElement loginButton  = driver.findElement(loginLocator);
        loginButton.click(); //FIXME
        return driver;
    }
}
