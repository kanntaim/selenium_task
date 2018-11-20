package ngaleev;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MainPage {
    private final By loginLocator = By.xpath("//span[contains(text(), \"Войти\")]/../..");
    private final By userNameLocator = By.className("user__name");

    private final WebDriverSingleton driver = WebDriverSingleton.getInstance();

    public MainPage() {
        if (!driver.getTitle().startsWith("Яндекс.Маркет")) {
            throw new IllegalStateException("This is not the main page");
        }
    }

    public LoginPage navigateLogin(){
        List<WebElement> loginButtons  = driver.findElements(loginLocator);
        for(WebElement button: loginButtons){
            if(button.isEnabled() && button.isDisplayed()){
                button.click();
                driver.switchTab(-1);

                return new LoginPage();
            }
        }
        throw new IllegalStateException("Login button not found");
    }

    public boolean CheckIsAuthorized(){
        return true;
    }
}

