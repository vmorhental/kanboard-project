package ui.pageactions;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import ui.models.LoginPage;
import ui.models.MainPage;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.open;

public class LoginPageActions extends LoginPage{
    @Step("We open login page")
    public LoginPageActions openLoginPage() {
        open("");
        return new LoginPageActions();
    }

    @Step("We login with username {0} and password {1}")
    public MainPageActions loginByUser(String username, String password) {
        getUsername().shouldBe(Condition.visible).sendKeys(username);
        getPassword().sendKeys(password);
        getLoginButton().click();
        return new MainPageActions();
    }
}