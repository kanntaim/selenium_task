package ngaleev;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginPage {
    private final By loginInputLocator =  By.name("login");
    private final By passwordInputLocator = By.name("passwd");



    private final WebDriverSingleton driver = WebDriverSingleton.getInstance();

    public LoginPage() {
        if (!"Авторизация".equals(driver.getTitle())) {
            throw new IllegalStateException("This is not the main page");
        }
    }

    public LoginPage authorize(String login, String pass){
        WebElement txbLogin = driver.findElement(loginInputLocator);
        txbLogin.sendKeys(login);
        txbLogin.submit();
        WebElement txbPassword = driver.findElement(passwordInputLocator);
        txbPassword.sendKeys(pass);
        txbPassword.submit();
        return this;
    }
}
