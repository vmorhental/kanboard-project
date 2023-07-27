package ui.tests;

import api.requestflows.ProjectRequestsFlow;
import api.requestflows.UserRequestsFlow;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ui.pageactions.LoginPageActions;

import static utils.RandomString.randomString;

public class ProjectTests extends BaseTest{
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "admin1";
    private static final String PROJECT_NAME = "ValeriiTest";
    private static final String USERNAME = "test" + randomString;
    private static final String PASSWORD = "test123!";
    UserRequestsFlow userSteps = new UserRequestsFlow();
    ProjectRequestsFlow projectSteps = new ProjectRequestsFlow();
    private Integer userId;
    private Integer projectId;

    @BeforeMethod
    public void prepareUserForLogin() {
        userId = userSteps.createRegularUser(USERNAME, PASSWORD);
        System.out.println("User is created with id " + userId);
        projectId = projectSteps.createProject("ValeriiTest", "Test description", Integer.valueOf(userId));
        System.out.println("Project is created with id " + projectId);
        projectSteps.linkProjectToCustomer(userId,projectId);
    }

    @Test
    @Description("This test checks that user can create project")
    public void createProjectTest(){
        SelenideElement actualProjectName = new LoginPageActions()
                .openLoginPage()
                .loginByUser(USERNAME, PASSWORD)
                .createProject(PROJECT_NAME)
                .getCreatedProjectName();
        actualProjectName.shouldBe(Condition.visible);
        actualProjectName.shouldHave(Condition.text(PROJECT_NAME));
    }

    @Test
    @Description("This test checks that created via API user can create Task in project created via API")
    public void createTaskInExistingProjectTest(){
        SelenideElement result = new LoginPageActions()
                .openLoginPage()
                .loginByUser(USERNAME, PASSWORD)
                .openProjectDetailsPage()
                .createTask()
                .getCreatedTaskBlock();
        result.shouldBe(Condition.visible);
    }

    @AfterMethod(alwaysRun = true)
    public void removeEntitiesAfterTest() {
        userSteps.deleteUser(userId);
        projectSteps.removeProject(projectId);
    }
}
