package ngaleev;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.annotation.Nullable;
import java.util.function.Predicate;

public class LoginPage {
    private final By loginInputLocator =  By.name("login");
    private final By passwordInputLocator = By.name("passwd");

    private final WebDriverSingleton driver = WebDriverSingleton.getInstance();

    public LoginPage() {
        if (!"Авторизация".equals(driver.getTitle())) {
            throw new IllegalStateException("This is not the main page");
        }
    }

    public LoginPage authorize(final String login, String pass){
        WebElement txbLogin = new WebDriverWait(driver, 60)
                .ignoring(StaleElementReferenceException.class)
                .until(new ExpectedCondition<WebElement>() {
                    @Override
                    public boolean apply(@Nullable WebDriver driver) { //FIXME https://stackoverflow.com/questions/12967541/how-to-avoid-staleelementreferenceexception-in-selenium
                        driver.findElement(loginInputLocator).sendKeys(login);
                        return true;
                    }
                });

        txbLogin.submit();
        WebElement txbPassword = driver.findElement(passwordInputLocator);
        txbPassword.sendKeys(pass);
        txbPassword.submit();
        return this;
    }
}
