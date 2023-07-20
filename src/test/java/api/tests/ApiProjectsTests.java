package api.tests;

import api.requestflows.ProjectRequestsFlow;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

import static utils.EnvProperties.ADMIN_USER_ID;

public class ApiProjectsTests {
    ProjectRequestsFlow projectRequestsSteps = new ProjectRequestsFlow();
    @Test
    @Description("This test checks that project can be created via API")
    public void createProjectTest() {
        Integer id = projectRequestsSteps.createProject("Valerii", "test descr", ADMIN_USER_ID);
        Assert.assertTrue(id>0,"Project was not created");
    }

    @Test
    @Description ("This test checks that created project can be removed via API")
    public void removeProjectTest(){
        Integer id = projectRequestsSteps.createProject("Valerii2", "test descr2", ADMIN_USER_ID);
        boolean isProjectRemoved = projectRequestsSteps.removeProject(id);
        Assert.assertEquals(isProjectRemoved, true, "Project was not removed");
    }
}
