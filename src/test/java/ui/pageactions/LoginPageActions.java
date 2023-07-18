package ui.pageactions;

import com.codeborne.selenide.Condition;
import ui.models.LoginPage;
import ui.models.MainPage;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.open;

public class LoginPageActions extends LoginPage{
    public LoginPageActions openLoginPage() {
        open("http://localhost:80");
        return new LoginPageActions();
    }

    public MainPage loginByUser(String username, String password) {
        getUsername().shouldBe(Condition.visible).sendKeys(username);
        getPassword().sendKeys(password);
        getLoginButton().click();
        return new MainPage();

    }
}