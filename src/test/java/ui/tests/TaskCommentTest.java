package ui.tests;

import api.requestflows.ProjectRequestsFlow;
import api.requestflows.TaskRequestFlow;
import api.requestflows.UserRequestsFlow;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ui.pageactions.LoginPageActions;

import static utils.RandomString.randomString;

public class TaskCommentTest extends BaseTest {
    private static final String PROJECT_NAME = "ValeriiTest";
    private static final String USERNAME = "test" + randomString;
    private static final String PASSWORD = "test123!";
    private static final String TASK_TITLE = "testTask";
    private static final String TASK_COMMENT_TEXT = "TestComment";
    UserRequestsFlow userSteps = new UserRequestsFlow();
    ProjectRequestsFlow projectSteps = new ProjectRequestsFlow();
    TaskRequestFlow taskSteps = new TaskRequestFlow();
    private Integer userId;
    private Integer projectId;
    private Integer taskId;

    @BeforeMethod
    public void prepareTestData() {
        userId = userSteps.createRegularUser(USERNAME, PASSWORD);
        System.out.println("User is created with id " + userId);
        projectId = projectSteps.createProject("ValeriiTest", "Test description", Integer.valueOf(userId));
        System.out.println("Project is created with id " + projectId);
        taskId = taskSteps.createTaskForCreatedProject(projectId, TASK_TITLE);
        System.out.println("Task is created with id " + taskId);
        projectSteps.linkProjectToCustomer(userId, projectId);
    }

    @Test
    @Description("This test checks user can add comment to a task")
    public void addCommentToTaskTest() {
        SelenideElement result = new LoginPageActions()
                .openLoginPage()
                .loginByUser(USERNAME, PASSWORD)
                .openProjectDetailsPage()
                .openTaskOptions()
                .addCommentToTask(TASK_COMMENT_TEXT)
                .openSavedCommentModal()
                .getSavedCommentText();

        Assert.assertEquals(result.getText(), TASK_COMMENT_TEXT, "Comment is not added");
    }

    @AfterMethod(alwaysRun = true)
    public void removeEntitiesAfterTest() {
        taskSteps.deleteTask(taskId);
        userSteps.deleteUser(userId);
        projectSteps.removeProject(projectId);
    }
}
