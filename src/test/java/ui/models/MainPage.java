package ui.models;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.Data;

import static com.codeborne.selenide.Selenide.$x;
@Data
public class MainPage extends BasePage {

    public static SelenideElement createProjectButton = $x("//div[@class='page-header']//a[@href='/project/create/personal']");
    public static SelenideElement projectNameInput = $x("//input[@id='form-name']");
    public static SelenideElement submitProjectCreationFormButton = $x("//button[@type='submit']");
    public static SelenideElement createdProjectLink = $x("//a[contains(text(),'ValeriiTest')]");

}
