package ngaleev;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {
    By loginLocator = By.xpath("//span[contains(text(), \"Войти\")]");

    private final WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;

        if (!"Яндекс.Маркет".equals(driver.getTitle().split(" ")[0])) {
            throw new IllegalStateException("This is not the main page");
        }
    }

    public LoginPage submitLogin(){
        driver.findElement(loginLocator).submit();
        return new LoginPage(driver);
    }
}
