import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import railway.constant.Constant;
import railway.model.User;
import railway.pages.HomePage;
import railway.pages.LoginPage;

public class HomePageTest {
    HomePage homePage;
    LoginPage loginPage;
    User user;

    @BeforeMethod
    public void setUp() {
        System.out.println("Init data");
        // khoi tao moi truong, data,...
        // khoi tao WebDriver
        Constant.WEBDRIVER = new ChromeDriver();

        // khoi tao cac page object
        homePage = new HomePage();
        loginPage = new LoginPage();
        user = new User("test@agest.vn", "Anhkhoa6118");

    }

    @AfterMethod
    public void tearDown() {
        System.out.println("Clean up driver");
        Constant.WEBDRIVER.quit();
    }

    @Test
    public void NegativeAllTabs() {
        homePage.open();

        homePage.goToLoginPage();
        homePage.goToRegisterPage();
        homePage.goToBookTicketPage();
        homePage.goToTicketPricePage();
        homePage.goToTimetablePage();
        homePage.goToContactPage();
        homePage.goToFAQPage();
        homePage.goToHomePage();
    }

    @Test
    public void NegativeAllTabsWhenLogged() {
        homePage.open();

        homePage.goToLoginPage();
        loginPage.login(user);


        homePage.goToMyTicketPage();
        homePage.goToBookTicketPage();
        homePage.goToTicketPricePage();
        homePage.goToTimetablePage();
        homePage.goToContactPage();
        homePage.goToFAQPage();
        homePage.goToHomePage();

        Assert.assertEquals(homePage.getGreetingText(), "Welcome " + user.getEmail());
    }
}
