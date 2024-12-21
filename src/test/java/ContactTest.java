import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import railway.constant.Constant;
import railway.model.User;
import railway.pages.ContactPage;
import railway.pages.HomePage;
import railway.pages.LoginPage;

public class ContactTest {
    HomePage homePage;
    ContactPage contactPage;
    LoginPage loginPage;
    User user;
    WebDriver driver;


    @BeforeMethod
    public void setUp() {
        System.out.println("Init data");
        // khoi tao moi truong, data,...
        // khoi tao WebDriver
        Constant.WEBDRIVER = new ChromeDriver();

        // khoi tao cac page object
        homePage = new HomePage();
        contactPage = new ContactPage();
        loginPage = new LoginPage();
        user = new User("test@agest.vn", "123456789");

    }

    @AfterMethod
    public void tearDown() {
        System.out.println("Clean up driver");
        Constant.WEBDRIVER.quit();
    }

    @Test
    public void testSelenium() {
        homePage.open();

        homePage.goToContactPage();
//        contactPage.clickOnlickEmail();

    }
}
