package railway.pages;

import org.openqa.selenium.By;
import railway.constant.Constant;
import railway.model.RegisterForm;
import railway.utils.DriverUtils;

public class RegisterPage extends BasePage {

    By emailInputLocator = By.id("email");
    By passwordInputLocator = By.id("password");
    By confirmPasswordInputLocator = By.id("confirmPassword");
    By pidInputLocator = By.id("pid");
    By registerButtonLocator = By.cssSelector("input[type=submit]");
    By registerSuccessMessageLocator = By.cssSelector("#content p");
    By registerErrorMessageLocator = By.cssSelector("p.message.error");
    public String getRegisterSuccessMessage() {
        return Constant.WEBDRIVER.findElement(registerSuccessMessageLocator).getText();
    }

    public void register(RegisterForm form) {
        enterEmail(form.getEmail());
        enterPassword(form.getPassword());
        enterConfirmPassword(form.getConfirmPassword());
        enterPID(form.getPid());
        DriverUtils.scrollIntoView(Constant.WEBDRIVER.findElement(registerButtonLocator));
        clickRegisterButton();
    }

    private void enterEmail(String email) {
        Constant.WEBDRIVER.findElement(emailInputLocator).sendKeys(email);
    }

    private void enterPassword(String password) {
        Constant.WEBDRIVER.findElement(passwordInputLocator).sendKeys(password);
    }

    private void enterConfirmPassword(String confirmPassword) {
        Constant.WEBDRIVER.findElement(confirmPasswordInputLocator).sendKeys(confirmPassword);
    }

    private void enterPID(String pid) {
        Constant.WEBDRIVER.findElement(pidInputLocator).sendKeys(pid);
    }

    private void clickRegisterButton() {
        Constant.WEBDRIVER.findElement(registerButtonLocator).click();
    }

}
