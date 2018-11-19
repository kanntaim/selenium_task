package ngaleev;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    private final By loginInputLocator =  By.xpath("//span[contains(text(), \"Логин или номер телефона\")]/../..//input");//FIXME 3 login pages
    //private final By passwordInputLocator =



    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;

        if (!"Авторизация".equals(driver.getTitle())) {
            throw new IllegalStateException("This is not the main page");
        }
    }

    public LoginPage inputLogin(String login){
        WebElement inputField = driver.findElement(loginInputLocator);
        try {
            inputField.sendKeys(login);
            inputField.submit();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        return this;
    }
}
