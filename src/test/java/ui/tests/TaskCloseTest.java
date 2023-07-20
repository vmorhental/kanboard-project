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

public class TaskCloseTest extends BaseTest {
    private static final String PROJECT_NAME = "ValeriiTest";
    private static final String USERNAME = "test" + randomString;
    private static final String PASSWORD = "test123!";
    private static final String TASK_TITLE = "testTask";
    UserRequestsFlow userSteps = new UserRequestsFlow();
    ProjectRequestsFlow projectSteps = new ProjectRequestsFlow();
    TaskRequestFlow taskSteps = new TaskRequestFlow();
    private Integer userId;
    private Integer projectId;
    private Integer taskId;
    @BeforeMethod
    public void prepareTestData() {
        userId = userSteps.createRegularUser(USERNAME, PASSWORD);
        System.out.println(userId);
        projectId = projectSteps.createProject("ValeriiTest", "Test description", Integer.valueOf(userId));
        System.out.println(projectId);
        taskId = taskSteps.createTaskForCreatedProject(projectId,TASK_TITLE);
        System.out.println(taskId);
        projectSteps.linkProjectToCustomer(userId,projectId);
    }

    @Test
    @Description("This test checks that task can be closed by user")
    public void closeTaskInExistingProjectTest() {
        SelenideElement result = new LoginPageActions()
                .openLoginPage()
                .loginByUser(USERNAME, PASSWORD)
                .openProjectDetailsPage()
                .openTaskOptions()
                .assignTaskToCurrentUser()
                .closeTask()
                .getCreatedTaskBlock();
        Assert.assertTrue(result.exists(), "Task was not closed");

    }

    @AfterMethod(alwaysRun = true)
    public void removeEntitiesAfterTest() {
        taskSteps.deleteTask(taskId);
        userSteps.deleteUser(userId);
        projectSteps.removeProject(projectId);
    }
}
