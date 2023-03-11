package tests;

import io.qameta.allure.*;
import listeners.TestListener;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.LoginPage;

@Listeners(TestListener.class)
public class LoginTest extends BaseTest {
    LoginPage loginPage;

    @BeforeMethod(alwaysRun = true)
    public void localSetup() {
        loginPage = new LoginPage(driver);
    }

    @Test
    @Description("Login user: Expected result - User is logged in, text is present on the page")
    @Epic("Login epic")
    @Story("Happy path login story")
    @Severity(SeverityLevel.BLOCKER)
    public void loginTestUser() {
        loginPage.goToLoginForm()
                .loginUser("alvera.heidenreich@hotmail.com", "sp4lbo0jr");
        Assert.assertTrue(loginPage.isUserLoggedIn());
    }

    @Test(groups = "negativeCases")
    public void loginTestNegativeCaseUsernameBlankPasswordBlank() {
        loginPage.goToLoginForm()
                .loginUser("", "");
        Assert.assertTrue(loginPage.loginNegativeCaseUsernameBlankPasswordBlank());
    }

    @Test(groups = "negativeCases")
    public void loginTestNegativeCaseUsernameInvalidPasswordValid() {
        loginPage.goToLoginForm()
                .loginUser("nekimail", "sp4lbo0jr");
        Assert.assertTrue(loginPage.loginNegativeCaseUsernameInvalidPasswordValid());
    }

    @Test(groups = "negativeCases")
    public void loginTestNegativeCaseUsernameValidPasswordInvalid() {
        loginPage.goToLoginForm()
                .loginUser("alvera.heidenreich@hotmail.com", "aa");
        Assert.assertTrue(loginPage.loginNegativeCaseUsernameValidPasswordInvalid());
    }

}
