package railway.pages;

import org.openqa.selenium.By;
import railway.constant.Constant;

public class TimetablePage {
    private By emailTextBoxLocator = By.cssSelector("//a[@href=\"mailto:thanh.viet.le@logigear.com\"]");

    public void clickOnlickEmail() {
        Constant.WEBDRIVER.findElement(emailTextBoxLocator).click();
    }
}
