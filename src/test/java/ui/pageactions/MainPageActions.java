package ui.pageactions;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import ui.models.MainPage;
import ui.models.ProjectDetailsPage;
import ui.models.ProjectHomePage;

public class MainPageActions extends MainPage {
    @Step("We create project with name {0}")
    public ProjectHomePage createProject(String projectName) {
        createProjectButton.shouldBe(Condition.visible).click();
        projectNameInput.sendKeys(projectName);
        submitProjectCreationFormButton.click();
        return new ProjectHomePage();
    }

    @Step("We open project Details page")
    public ProjectDetailsPageActions openProjectDetailsPage() {
        createdProjectLink.shouldBe(Condition.visible).click();
        return new ProjectDetailsPageActions();
    }
}
