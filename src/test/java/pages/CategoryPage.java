package pages;

import framework.drivers.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CategoryPage {
    private final By categoryNameLocator = By.tagName("h1");
    private final By categoryCurrentButtonLocator = By.xpath("//li[contains(@class, \"topmenu__item_mode_current\")]");

    private final WebDriver driver = WebDriver.getInstance();

    CategoryPage() {
        (new WebDriverWait(driver.getDriver(), 5))
                .until(ExpectedConditions.visibilityOfElementLocated(categoryNameLocator));
    }

    public boolean compareCategoryNames() {
        String buttonText = driver.findElement(categoryCurrentButtonLocator).getAttribute("data-department");
        String pageText = driver.findElement(categoryNameLocator).getText();
        return CategoryNamesMap.compareCategoryName(buttonText, pageText);
    }


}
