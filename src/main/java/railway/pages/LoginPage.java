package railway.pages;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import railway.constant.Constant;
import railway.model.User;
import railway.utils.DriverUtils;

public class LoginPage extends BasePage {
    WebDriver driver;

    private By emailTextBoxLocator = By.id("username");
    private By passwordTextBoxLocator = By.id("password");
    private By loginButtonLocator = By.cssSelector("input[value=Login]");

    private By messageErrorLocator = By.cssSelector("p.message");
    private By messageUsernameErrorLocator = By.xpath("//label[contains(@class, 'validation-error') and contains(text(), 'You must specify a username.')]");
    private By messagePasswordErrorLocator = By.xpath("//label[contains(@class, 'validation-error') and contains(text(), 'You must specify a password.')]");

    private WebElement getLoginButton() {
        return Constant.WEBDRIVER.findElement(loginButtonLocator);
    }

    public void login(User user) {
        enterEmail(user.getEmail());
        enterPassword(user.getPassword());
        DriverUtils.scrollIntoView(getLoginButton());
//        getErrorMessage(user.getEmail());
        clickLoginButton();
    }

    private void enterEmail(String email) {
        Constant.WEBDRIVER.findElement(emailTextBoxLocator).sendKeys(email);
    }

    private void enterPassword(String password) {
        Constant.WEBDRIVER.findElement(passwordTextBoxLocator).sendKeys(password);
    }

    public String getErrorMessage() {
        return Constant.WEBDRIVER.findElement(messageErrorLocator).getText();
    }

    public String getMessagePasswordError() {
        return Constant.WEBDRIVER.findElement(messageUsernameErrorLocator).getText();
    }

    public String getMessageUsernameError() {
        return Constant.WEBDRIVER.findElement(messagePasswordErrorLocator).getText();
    }

//    public void getErrorMessage(String email) {
//        Constant.WEBDRIVER.findElement(errorMessageLocator).sendKeys(email);
//    }

    public boolean isDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            WebElement messageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("p.message")));
            return messageElement.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }


    private void clickLoginButton() {
        getLoginButton().click();
    }
}
