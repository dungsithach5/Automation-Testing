package railway.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import railway.constant.Constant;

import java.time.Duration;

public class HomePage extends BasePage {
    WebDriver driver;

    private By logoutTabLocator = By.id("//a[span[text()='Log out']]");

    public void open() {
        Constant.WEBDRIVER.get(Constant.RAILWAY_URL);
    }

    public boolean isLogoutTabVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            WebElement logoutTab = wait.until(ExpectedConditions.visibilityOfElementLocated(logoutTabLocator));
            return logoutTab.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
