package railway.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import railway.constant.Constant;

public class ChangePasswordPage extends BasePage {
    private By currentPasswordTextBoxLocator = By.id("currentPassword");
    private By newPasswordTextBoxLocator = By.id("newPassword");
    private By confirmPasswordTextBoxLocator = By.id("confirmPassword");
    private By changePasswordButtonLocator = By.cssSelector("input[value='Change Password']");
    private By messageSuccessLocator = By.cssSelector("p.message.success");
    private By messageErrorLocator = By.cssSelector("p.message.error");

    private WebElement getChangePasswordButton() {
        return Constant.WEBDRIVER.findElement(changePasswordButtonLocator);
    }

    public void changePassword(String currentPassword, String newPassword, String confirmPassword) {
        enterCurrentPassword(currentPassword);
        enterNewPassword(newPassword);
        enterConfirmPassword(confirmPassword);
        clickChangePasswordButton();
    }

    private void enterCurrentPassword(String currentPassword) {
        Constant.WEBDRIVER.findElement(currentPasswordTextBoxLocator).sendKeys(currentPassword);
    }

    private void enterNewPassword(String newPassword) {
        Constant.WEBDRIVER.findElement(newPasswordTextBoxLocator).sendKeys(newPassword);
    }

    private void enterConfirmPassword(String confirmPassword) {
        Constant.WEBDRIVER.findElement(confirmPasswordTextBoxLocator).sendKeys(confirmPassword);
    }

    private void clickChangePasswordButton() {
        WebElement button = getChangePasswordButton();
        JavascriptExecutor js = (JavascriptExecutor) Constant.WEBDRIVER;
        js.executeScript("arguments[0].click();", button);
    }

    public String getErrorMessage() {
        return Constant.WEBDRIVER.findElement(messageErrorLocator).getText();
    }

    public String getSuccessMessage() {
        return Constant.WEBDRIVER.findElement(messageSuccessLocator).getText();
    }
}