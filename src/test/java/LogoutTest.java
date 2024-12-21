import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import railway.constant.Constant;
import railway.model.User;
import railway.pages.HomePage;
import railway.pages.LoginPage;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class LogoutTest {
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
    public void LogoutSuccessfull() {
        homePage.open();
        homePage.goToLoginPage();
        loginPage.login(user);
        homePage.goToLogoutPage();
    }

    @Test
    public void LogoutandLoginAgain() {
        homePage.open();
        homePage.goToLoginPage();
        loginPage.login(user);
        homePage.goToLogoutPage();
        homePage.goToLoginPage();
        loginPage.login(user);
    }

    @Test
    public void accessRestrictedPageAfterLogout() {
        homePage.open();
        homePage.goToLoginPage();
        loginPage.login(user);
        homePage.goToRestrictedPage();
        homePage.goToLogoutPage();
        Constant.WEBDRIVER.navigate().back();
        assertFalse(loginPage.isDisplayed(), "User should be redirected to login page after logout");
    }

    @Test
    public void LogoutTabVisibility() {
        homePage.open();
        assertFalse(homePage.isLogoutTabVisible(), "Log out tab should not be visible before logging in");

        homePage.goToLoginPage();
        loginPage.login(user);
        assertFalse(homePage.isLogoutTabVisible(), "Log out tab should be visible after logging in");

        homePage.goToLogoutPage();
        assertFalse(homePage.isLogoutTabVisible(), "Log out tab should not be visible after logging out");
    }
}
