import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import railway.constant.Constant;
import railway.model.User;
import railway.pages.HomePage;
import railway.pages.LoginPage;
import railway.pages.TicketPricePage;

public class TicketPriceTest {
    HomePage homePage;
    LoginPage loginPage;
    TicketPricePage ticketPricePage;
    User user;
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        System.out.println("Init data");
        Constant.WEBDRIVER = new ChromeDriver();
        homePage = new HomePage();
        loginPage = new LoginPage();
        ticketPricePage = new TicketPricePage();
        user = new User("test@agest.vn", "123456789");
    }

    @AfterMethod
    public void tearDown() {
        System.out.println("Clean up driver");
        Constant.WEBDRIVER.quit();
    }

    @Test
    public void testUserCanCheckTicketPrices() {
        homePage.open();
        homePage.goToTicketPricePage();

        Assert.assertTrue(ticketPricePage.isTicketPriceTableDisplayed(), "Ticket price table is not displayed");
    }
}