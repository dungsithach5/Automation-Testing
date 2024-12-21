package railway.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import railway.constant.Constant;

public class MyTicketPage extends BasePage {

    public void cancelTicketByID(int id) {
        By cancelButton = By.xpath(String.format(String.format("//input[@value='Cancel'][@onclick='DeleteTicket(%d);']", id)));
        Constant.WEBDRIVER.findElement(cancelButton).click();
        Alert alert = Constant.WEBDRIVER.switchTo().alert();
        alert.accept();
    }

    public boolean isCancelTicketButtonDisplayed(int id) {
        By cancelButton = By.xpath(String.format(String.format("//input[@value='Cancel'][@onclick='DeleteTicket(%d);']", id)));
        return !Constant.WEBDRIVER.findElements(cancelButton).isEmpty();
    }



}
