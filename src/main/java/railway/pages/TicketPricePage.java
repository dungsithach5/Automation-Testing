package railway.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import railway.constant.Constant;

public class TicketPricePage {
    private WebDriver driver = Constant.WEBDRIVER;
    private By ticketPriceTable = By.id("ticketPriceTable");

    public boolean isTicketPriceTableDisplayed() {
        return driver.findElement(ticketPriceTable).isDisplayed();
    }
}