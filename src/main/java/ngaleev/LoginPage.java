package ngaleev;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginPage {
    private final By loginInputLocator =  By.xpath("//span[contains(text(), \"Логин или номер телефона\")" +
                                                        "or contains(text(), \"Логин\")" +
                                                        "or contains(text(), \"Введите почту или телефон\")]/../..//input"
                                                    );
    private final By passwordInputLocator = By.xpath("//span[contains(text(), \"Пароль\")]/../..//input");



    private final WebDriverSingleton driver = WebDriverSingleton.getInstance();

    public LoginPage() {
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

    public LoginPage inputPassword(String pass){
        WebElement inputField = driver.findElement(passwordInputLocator);
        try {
            inputField.sendKeys(pass);
            inputField.submit();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        return this;
    }
}
