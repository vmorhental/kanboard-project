package ui.pageactions;

import io.qameta.allure.Step;
import ui.models.ProjectDetailsPage;

public class ProjectDetailsPageActions extends ProjectDetailsPage {

    private String taskTitle = "testTask";

    @Step("We create task with title - \"testTask\" ")
    public ProjectDetailsPage createTask() {
        addBacklogTaskButton.click();
        taskTitleInputField.sendKeys(taskTitle);
        createTaskButton.click();
        return new ProjectDetailsPage();
    }
@Step("We open task options drop down by click on arrow down icon")
    public ProjectDetailsPageActions openTaskOptions() {
        createdTaskOptionsDDL.click();
        return new ProjectDetailsPageActions();
    }
    @Step("We assign Task to current user")
    public ProjectDetailsPageActions assignTaskToCurrentUser() {
        assignToMeButton.click();
        return new ProjectDetailsPageActions();
    }
    @Step("We close task by click on Close task option in task options")
    public ProjectDetailsPage closeTask() {
        createdTaskOptionsDDL.click();
        closeTaskButton.click();
        confirmCloseTaskButton.click();
        return new ProjectDetailsPage();
    }
    @Step("We add comment with text {0} for a task")
    public ProjectDetailsPageActions addCommentToTask(String commentText) {
        addCommentButton.click();
        commentTextArea.sendKeys(commentText);
        saveCommentButton.click();
        return new ProjectDetailsPageActions();
    }
    @Step("We open saved comments pop up")
    public ProjectDetailsPage openSavedCommentModal() {
        savedCommentsIcon.click();
        return new ProjectDetailsPage();
    }

}
