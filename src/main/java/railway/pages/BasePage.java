package railway.pages;

import org.openqa.selenium.By;
import railway.constant.Constant;

public class BasePage {
    //
    private By loginTabLocator = By.xpath("//a[span[text()='Login']]");
    private By registerTabLocator = By.xpath("//a[span[text()='Register']]");
    private By bookTicketTabLocator = By.xpath("//a[span[text()='Book ticket']]");
    private By myTicketTabLocator = By.xpath("//a[span[text()='My ticket']]");
    private By TicketPriceTabLocator = By.xpath("//a[span[text()='Ticket price']]");
    private By TimetableTabLocator = By.xpath("//a[span[text()='Timetable']]");
    private By ContactTabLocator = By.xpath("//a[span[text()='Contact']]");
    private By FAQTabLocator = By.xpath("//a[span[text()='FAQ']]");
    private By HomeTabLocator = By.xpath("//a[span[text()='Home']]");
    private By logoutTabLocator = By.xpath("//a[span[text()='Log out']]");
    private By changePasswordTabLocator = By.xpath("//a[span[text()='Change password']]");
    private By greetingLabelLocator = By.cssSelector("div.account strong");
    private By headerLocator = By.tagName("h1");

    public String getHeaderText() {
        return Constant.WEBDRIVER.findElement(headerLocator).getText();
    }

    public void goToLoginPage() {
        Constant.WEBDRIVER.findElement(loginTabLocator).click();
    }

    public void goToRegisterPage() {
        Constant.WEBDRIVER.findElement(registerTabLocator).click();
    }

    public void goToBookTicketPage() {
        Constant.WEBDRIVER.findElement(bookTicketTabLocator).click();
    }

    public void goToTicketPricePage() {
        Constant.WEBDRIVER.findElement(TicketPriceTabLocator).click();
    }

    public void goToTimetablePage() {
        Constant.WEBDRIVER.findElement(TimetableTabLocator).click();
    }

    public void goToContactPage() {
        Constant.WEBDRIVER.findElement(ContactTabLocator).click();
    }

    public void goToFAQPage() {
        Constant.WEBDRIVER.findElement(FAQTabLocator).click();
    }

    public void goToHomePage() {
        Constant.WEBDRIVER.findElement(HomeTabLocator).click();
    }

    public void goToChangePasswordPage() {
        Constant.WEBDRIVER.findElement(changePasswordTabLocator).click();
    }

    public void goToLogoutPage() { Constant.WEBDRIVER.findElement(logoutTabLocator).click();}

    public void goToMyTicketPage() {
        Constant.WEBDRIVER.findElement(myTicketTabLocator).click();
    }


    public String getGreetingText() {
        return Constant.WEBDRIVER.findElement(greetingLabelLocator).getText();
    }

    public void goToRestrictedPage() {
        Constant.WEBDRIVER.get(Constant.RAILWAY_URL);
    }
}
