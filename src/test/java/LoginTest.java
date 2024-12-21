import com.github.javafaker.Faker;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import railway.constant.Constant;
import railway.model.User;
import railway.pages.HomePage;
import railway.pages.LoginPage;

public class LoginTest {
    HomePage homePage;
    LoginPage loginPage;
    Faker faker;
    User user;
    User NonPassword;
    User NonUser;
    User EmailwithSQLquery;
    User PasswordwithSQLquery;
    User EmailBlank;
    User PasswordBlank;

    @BeforeMethod
    public void setUp() {
        System.out.println("Init data");
        // khoi tao moi truong, data,...
        // khoi tao WebDriver
        Constant.WEBDRIVER = new ChromeDriver();

        // khoi tao cac page object
        homePage = new HomePage();
        loginPage = new LoginPage();
        user = new User("test@agest.vn", "123456789");
        NonPassword = new User("test@agest.vn", "adadad");
        EmailwithSQLquery = new User("' OR 1=1 --", "123456789");
        PasswordwithSQLquery = new User("test@agest.vn", "' OR 1=1 --");
        EmailBlank = new User("", "123456789");
        PasswordBlank = new User("test@agest.vn", "");
        faker = new Faker();
        NonUser = new User(faker.internet().emailAddress(),faker.internet().password());

    }

    @AfterMethod
    public void tearDown() {
        System.out.println("Clean up driver");
        Constant.WEBDRIVER.quit();
    }

    @Test
    public void ValidAccount() {
        homePage.open();

        homePage.goToLoginPage();

        loginPage.login(user);

        Assert.assertEquals(homePage.getGreetingText(), "Welcome " + user.getEmail());
    }

    @Test
    public void NonExistedAccount() {
        homePage.open();

        homePage.goToLoginPage();

        loginPage.login(NonUser);

        Assert.assertEquals(loginPage.getErrorMessage(), "Invalid username or password. Please try again.");
    }

    @Test
    public void InvalidPasswordAccount() {
        homePage.open();

        homePage.goToLoginPage();

        loginPage.login(NonPassword);

        Assert.assertEquals(loginPage.getErrorMessage(), "Invalid username or password. Please try again.");
    }

    @Test
    public void AccountWithEmailSQLquery() {
        homePage.open();

        homePage.goToLoginPage();

        loginPage.login(EmailwithSQLquery);

        Assert.assertEquals(loginPage.getErrorMessage(), "Invalid username or password. Please try again.");
    }

    @Test
    public void AccountWithPasswordSQLquery() {
        homePage.open();

        homePage.goToLoginPage();

        loginPage.login(PasswordwithSQLquery);

        Assert.assertEquals(loginPage.getErrorMessage(), "Invalid username or password. Please try again.");
    }

    @Test
    public void AccountWithEmailBlank() {
        homePage.open();

        homePage.goToLoginPage();

        loginPage.login(EmailBlank);

        Assert.assertEquals(loginPage.getErrorMessage(), "There was a problem with your login and/or errors exist in your form.");
    }

    @Test
    public void AccountWithPasswordBlank() {
        homePage.open();

        homePage.goToLoginPage();

        loginPage.login(PasswordBlank);

        Assert.assertEquals(loginPage.getErrorMessage(), "There was a problem with your login and/or errors exist in your form.");
    }

}
