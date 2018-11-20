package ngaleev;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private final By loginInputLocator =  By.name("login");
    private final By passwordInputLocator = By.name("passwd");

    private final WebDriverSingleton driver = WebDriverSingleton.getInstance();

    public LoginPage() {
        (new WebDriverWait(driver, 5))
                .until(ExpectedConditions.elementToBeClickable(loginInputLocator));
        if (!"Авторизация".equals(driver.getTitle())) {
            throw new IllegalStateException("This is not the login page");
        }
    }

    public LoginPage authorize(final String login, String pass){
        WebDriverWait wait = new WebDriverWait(driver, 5);

        WebElement txbLogin = wait.until(ExpectedConditions.elementToBeClickable(loginInputLocator));
        txbLogin.sendKeys(login);
        wait.until(ExpectedConditions.elementToBeClickable(loginInputLocator));
        txbLogin.submit();

        WebElement txbPassword = wait.until(ExpectedConditions.elementToBeClickable(passwordInputLocator));
        txbPassword.sendKeys(pass);
        wait.until(ExpectedConditions.elementToBeClickable(passwordInputLocator));
        txbPassword.submit();
        return this;
    }
}
