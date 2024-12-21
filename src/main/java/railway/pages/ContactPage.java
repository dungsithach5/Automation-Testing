package railway.pages;

import org.openqa.selenium.By;
import railway.constant.Constant;

public class ContactPage extends BasePage {
    private By emailTextBoxLocator = By.cssSelector("//a[@href=\"mailto:thanh.viet.le@logigear.com\"]");



    public void clickOnlickEmail() {
        Constant.WEBDRIVER.findElement(emailTextBoxLocator).click();
    }
}
