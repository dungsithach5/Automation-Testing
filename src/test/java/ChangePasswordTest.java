import com.github.javafaker.Faker;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import railway.constant.Constant;
import railway.model.User;
import railway.pages.ChangePasswordPage;
import railway.pages.HomePage;
import railway.pages.LoginPage;

public class ChangePasswordTest {
    HomePage homePage;
    LoginPage loginPage;
    ChangePasswordPage changePasswordPage;
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
        changePasswordPage = new ChangePasswordPage();
        user = new User("test@agest.vn", "Anhkhoa6118");

    }

    @AfterMethod
    public void tearDown() {
        System.out.println("Clean up driver");
        Constant.WEBDRIVER.quit();
    }

    @Test
    public void ChangePasswordCorrectly() {
        homePage.open();
        homePage.goToLoginPage();
        loginPage.login(user);
        homePage.goToChangePasswordPage();
        changePasswordPage.changePassword("123456789", "Anhkhoa6118", "Anhkhoa6118");
        Assert.assertEquals(changePasswordPage.getSuccessMessage(), "Your password has been updated!");
    }

    @Test
    public void ChangePasswordWithIncorrectCurrentPassword() {
        homePage.open();
        homePage.goToLoginPage();
        loginPage.login(user);
        homePage.goToChangePasswordPage();
        changePasswordPage.changePassword("wrongpassword", "Anhkhoa6118", "Anhkhoa6118");
        Assert.assertEquals(changePasswordPage.getErrorMessage(), "An error occurred when attempting to change the password. Maybe your current password is incorrect.");
    }

    @Test
    public void ChangePasswordWithNonMatchingConfirmation() {
        homePage.open();
        homePage.goToLoginPage();
        loginPage.login(user);
        homePage.goToChangePasswordPage();
        changePasswordPage.changePassword("Anhkhoa6118", "Anhkhoa6118", "Anhkhoa123213");
        Assert.assertEquals(changePasswordPage.getErrorMessage(), "Password change failed. Please correct the errors and try again.");
    }

    @Test
    public void ChangePasswordWithShortNewPassword() {
        homePage.open();
        homePage.goToLoginPage();
        loginPage.login(user);
        homePage.goToChangePasswordPage();
        changePasswordPage.changePassword("Anhkhoa6118", "12345", "12345");
        Assert.assertEquals(changePasswordPage.getErrorMessage(), "Password change failed. Please correct the errors and try again.");
    }

    @Test
    public void ChangePasswordWithEmptyCurrentPassword() {
        homePage.open();
        homePage.goToLoginPage();
        loginPage.login(user);
        homePage.goToChangePasswordPage();
        changePasswordPage.changePassword("", "Anhkhoa6118", "Anhkhoa6118");
        Assert.assertEquals(changePasswordPage.getErrorMessage(), "Password change failed. Please correct the errors and try again.");
    }

    @Test
    public void ChangePasswordWithEmptyConfirmationPassword() {
        homePage.open();
        homePage.goToLoginPage();
        loginPage.login(user);
        homePage.goToChangePasswordPage();
        changePasswordPage.changePassword("Anhkhoa6118", "Anhkhoa6118", "");
        Assert.assertEquals(changePasswordPage.getErrorMessage(), "Password change failed. Please correct the errors and try again.");
    }

    @Test
    public void ChangePasswordWithAllFieldsEmpty() {
        homePage.open();
        homePage.goToLoginPage();
        loginPage.login(user);
        homePage.goToChangePasswordPage();
        changePasswordPage.changePassword("", "", "");
        Assert.assertEquals(changePasswordPage.getErrorMessage(), "Password change failed. Please correct the errors and try again.");
    }

    @Test
    public void ChangePasswordWithExceedingMaxLengthNewPassword() {
        homePage.open();
        homePage.goToLoginPage();
        loginPage.login(user);
        homePage.goToChangePasswordPage();
        String longPassword = "A".repeat(101);
        changePasswordPage.changePassword("Anhkhoa6118", longPassword, longPassword);
        Assert.assertEquals(changePasswordPage.getErrorMessage(), "Password change failed. Please correct the errors and try again.");
    }
}
