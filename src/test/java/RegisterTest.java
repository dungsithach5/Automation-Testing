import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import railway.model.RegisterForm;
import railway.pages.HomePage;
import railway.pages.LoginPage;
import railway.pages.RegisterPage;

public class RegisterTest extends TestBase {

    HomePage homePage;
    RegisterPage registerPage;
    LoginPage loginPage;
    RegisterForm registerForm;
    Faker faker;
    String password;
    String pid;

    @BeforeMethod
    public void initData() {
        homePage = new HomePage();
        registerPage = new RegisterPage();
        loginPage = new LoginPage();
        faker = new Faker();

        password = faker.numerify("########");
        pid = faker.numerify("########");
        registerForm = new RegisterForm(faker.internet().emailAddress(), password, password, pid);
    }


    @Test
    public void verifyUserCanRegisterSuccessfully() {
        homePage.open();

        homePage.goToRegisterPage();

        registerPage.register(registerForm);

        Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "You're here");

        registerPage.goToLoginPage();

        loginPage.login(registerForm.toUser());

        Assert.assertEquals(homePage.getGreetingText(), "Welcome " + registerForm.getEmail());
    }

}
