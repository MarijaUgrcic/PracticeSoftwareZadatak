package pages;

import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import utils.Utils;

import java.util.Locale;

public class RegisterPage extends BasePage {
    private By signInButton = By.cssSelector("a[data-test='nav-sign-in']");
    private By registerYourAccountLink = By.cssSelector("a[data-test='register-link']");
    private By firstName = By.id("first_name");
    private By lastName = By.id("last_name");
    private By dateOFBirth = By.id("dob");
    private By address = By.id("address");
    private By postcode = By.id("postcode");
    private By city = By.id("city");
    private By state = By.id("state");
    private By country = By.id("country");
    private By phone = By.id("phone");
    private By email = By.id("email");
    private By passwordField = By.id("password");
    private By registerButton = By.cssSelector("button[data-test='register-submit']");
    private By loginButton = By.cssSelector("input[value='Login']");
    private By myAccountText = By.xpath("//h1[text()='My account']");
    private By myAccountMenuProfile = By.cssSelector("a[data-test='nav-profile']");

    String userName;
    String password;
    private static final Logger log = LogManager.getLogger(RegisterPage.class.getName());
    Faker faker = new Faker(new Locale("en-US"));

    public RegisterPage(WebDriver driver) {
        super(driver);
    }
    @Step("Register user through register form")
    public RegisterPage registerUser() {
        userName = faker.internet().emailAddress();
        password = faker.internet().password();
        log.info("Username: " + userName);
        log.info("Password: " + password);

        typeIn(firstName, faker.name().firstName());

        typeIn(lastName, faker.name().lastName());
        typeIn(dateOFBirth, "1212" + Keys.TAB + "1990");
        typeIn(address, faker.address().fullAddress());
        typeIn(postcode, faker.number().digits(6));
        typeIn(city, faker.address().city());
        typeIn(state, faker.address().state());
        selectCountry();
        typeIn(phone, faker.number().digits(10));
        typeIn(email, userName);
        typeIn(passwordField, password);
        clickOnElement(registerButton);
        Utils.waitForSeconds(2);
        return this;
    }

    @Step("DOB entry")
    private String dob() {
        if (driver instanceof ChromeDriver) {
            return "1212" + Keys.TAB + "1929";
        } else if (driver instanceof FirefoxDriver) {
            return "12/" + "12/" + "1229";
        } else if (driver instanceof EdgeDriver) {
            return "1212" + Keys.ARROW_RIGHT + "1929";
        }
        return null;
    }
    @Step("Country selection")
    private void selectCountry() {
        Select select = new Select(getElement(country));
        select.selectByValue("RS");
    }

    @Step("Go to register page")
    public RegisterPage goToRegisterPage() {
        clickOnElement(signInButton);
        clickOnElement(registerYourAccountLink);
        return this;
    }

    @Step("Verifying user is successfully registered")
    public boolean isUserRegistered() {
        return matchesExpectedText(myAccountText, "My account") &&
                matchesExpectedText(myAccountMenuProfile, " Profile");
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
