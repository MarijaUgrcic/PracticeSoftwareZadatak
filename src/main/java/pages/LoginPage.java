package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.Utils;

public class LoginPage extends BasePage {
    private By email = By.id("email");
    private By passwordField = By.id("password");
    private By loginButton = By.cssSelector("input[value='Login']");
    private By signInButton = By.cssSelector("a[data-test='nav-sign-in']");
    private By userNameInMenu = By.id("user-menu");
    private By errorEmailFieldBlank = By.xpath("//div[contains(text(),'E-mail is required.')]");
    private By errorPasswordFieldBlank = By.xpath("//div[contains(text(),'Password is required.')]");
    private By errorEmailFieldFormatInvalid = By.xpath("//div[contains(text(),'E-mail format is invalid.')]");
    private By errorPasswordFieldFormatInvalid = By.xpath("//div[contains(text(),'Password length is invalid')]");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage goToLoginForm() {
        clickOnElement(signInButton);
        return this;
    }

    @Step("Filling in login form with username and password")
    public LoginPage loginUser(String username, String password) {
        Utils.waitForSeconds(1);
        typeIn(email, username);
        typeIn(passwordField, password);
        clickOnElement(loginButton);
        return this;
    }

    public boolean isUserLoggedIn() {
        return matchesExpectedText(userNameInMenu, "Renate Kovacek");
    }

    public boolean loginNegativeCaseUsernameBlankPasswordBlank() {
        return matchesExpectedText(errorEmailFieldBlank, "E-mail is required.") &&
        matchesExpectedText(errorPasswordFieldBlank, "Password is required.");
    }

    public boolean loginNegativeCaseUsernameInvalidPasswordValid() {
        return matchesExpectedText(errorEmailFieldFormatInvalid, "E-mail format is invalid.") &&
                errorFieldNotDisplayed(errorPasswordFieldFormatInvalid);
    }

    public boolean loginNegativeCaseUsernameValidPasswordInvalid() {
        return errorFieldNotDisplayed(errorEmailFieldFormatInvalid) &&
                matchesExpectedText(errorPasswordFieldFormatInvalid, "Password length is invalid");
    }

}
