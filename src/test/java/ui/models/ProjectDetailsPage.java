package ui.models;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$x;

@Getter

public class ProjectDetailsPage extends BasePage {
    public static SelenideElement addBacklogTaskButton = $x("//a[contains(text(),'Backlog')]/../../..//a[@title='Add a new task']");
    public static SelenideElement taskTitleInputField = $x("//input[@name='title']");
    public static SelenideElement createTaskButton = $x("//button[@type='submit']");
    public static SelenideElement createdTaskBlock = $x("//a[contains(text(),'testTask')]");
    public static SelenideElement createdTaskOptionsDDL = $x("//div[@class='task-board-header']/div[@class='dropdown']/a[@class='dropdown-menu dropdown-menu-link-icon']");
    public static SelenideElement assignToMeButton = $x("//ul[@class='dropdown-submenu-open']//a[contains(text(),'Assign to me')]");
    public static SelenideElement addCommentButton = $x("//ul[@class='dropdown-submenu-open']//a[contains(text(),'Add a comment')]");
    public static SelenideElement commentTextArea = $x("//textarea[@name='comment']");
    public static SelenideElement saveCommentButton = $x("//button[@type='submit']");
    public static SelenideElement savedCommentsIcon = $x("//div[@class='task-board-icons-row']//i[@class='fa fa-comments-o fa-fw js-modal-medium']");
    public static SelenideElement savedCommentText = $x("//p[contains(text(),'TestComment')]");
    public static SelenideElement closeTaskButton = $x("//ul[@class='dropdown-submenu-open']//a[contains(text(),'Close this task')]");
    public static SelenideElement confirmCloseTaskButton = $x("//button[@id='modal-confirm-button']");

    public SelenideElement getSavedCommentText() {
        return savedCommentText;
    }

    public SelenideElement getCreatedTaskBlock() {
        return createdTaskBlock;
    }

}
