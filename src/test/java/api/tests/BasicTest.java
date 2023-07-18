package api.tests;

import api.requestflows.ProjectRequestsFlow;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BasicTest {
    ProjectRequestsFlow createProjectRequest = new ProjectRequestsFlow();
    @Test
    @Description("This test checks that project can be created via API")
    public void createProjectTest() {
        Integer id = createProjectRequest.createProject("Valerii", "test descr", 0);
        Assert.assertTrue(id>0,"Project was not created");
    }
}
