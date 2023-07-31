package ui.models;

import com.codeborne.selenide.SelenideElement;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import static com.codeborne.selenide.Selenide.$x;


public class ProjectHomePage extends BasePage {

    public SelenideElement projectName = $x("//span[@class='title']");

    public SelenideElement getCreatedProjectName() {
        projectName.getText();
        return projectName;
    }
}
