package ui.models;

import com.codeborne.selenide.SelenideElement;
import lombok.Data;

import static com.codeborne.selenide.Selenide.$x;
@Data

public class LoginPage {
    public SelenideElement username = $x("//input[@name='username']");
    public SelenideElement password = $x("//input[@name='password']");
    public SelenideElement loginButton =$x("//button[@type='submit']");

}
