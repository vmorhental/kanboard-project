package ui.tests;

import api.requestflows.UserRequestsFlow;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ui.models.LoginPage;
import ui.pageactions.LoginPageActions;

import static utils.EnvProperties.BASE_URL;
import static utils.RandomString.randomString;

public class LoginTests extends BaseTest{
    private static final String USERNAME = "test" + randomString;
    private static final String PASSWORD = "test123!";
    UserRequestsFlow userSteps = new UserRequestsFlow();
    private Integer userId;

    @BeforeMethod
    public void prepareUserForLogin() {
        userId = userSteps.createRegularUser(USERNAME, PASSWORD);
        System.out.println("User is created with id " + userId);
    }

    @Test
    @Description ("This test checks possibility to login with valid credentials after user was created via api request")
    public void loginPositiveTest() {
        new LoginPageActions()
                .openLoginPage()
                .loginByUser(USERNAME, PASSWORD);
        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        String expectedUrl = BASE_URL + "/";
        Assert.assertEquals(currentUrl, expectedUrl, "Login is unsuccessful");
    }

    @Test
    @Description ("This test checks user cannot login with invalid username")
    @Step ("Try to login with invalid username \"incorrectUsername\" and valid password")
    public void loginIncorrectUsernameTest() {
        new LoginPageActions()
                .openLoginPage()
                .loginByUser("incorrectUsername", PASSWORD);
        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        String expectedUrl = BASE_URL + "/?controller=AuthController&action=check";
        Assert.assertEquals(currentUrl, expectedUrl, "incorrect username is valid");
    }

    @Test
    @Description ("This test checks user cannot login with invalid password")
    @Step ("Try to login with valid username and invalid password \"incorrectPassword\"")
    public void loginIncorrectPasswordTest() {
        new LoginPageActions()
                .openLoginPage()
                .loginByUser(USERNAME, "incorrectPassword");
        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        String expectedUrl = BASE_URL + "/?controller=AuthController&action=check";
        Assert.assertEquals(currentUrl, expectedUrl, "incorrect password is valid");
    }

    @AfterMethod (alwaysRun = true)
    public void removeUserAfterTest() {
        userSteps.deleteUser(userId);
    }
}