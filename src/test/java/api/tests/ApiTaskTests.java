package api.tests;

import api.requestflows.ProjectRequestsFlow;
import api.requestflows.TaskRequestFlow;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ApiTaskTests extends BaseApiTest{
    TaskRequestFlow taskRequestsSteps = new TaskRequestFlow();

    @Test
    @Description("This test checks that task can be created")
    public void createTaskTest() {
        Integer id = taskRequestsSteps.createTaskForCreatedProject();
        Assert.assertTrue(id>0,"Task was not created");
    }

    @Test
    @Description("This test checks that task can be removed")
    public void removeTaskTest() {
        Integer id = taskRequestsSteps.createTaskForCreatedProject();
        boolean isTaskRemoved = taskRequestsSteps.deleteTask(id);
        Assert.assertEquals(isTaskRemoved, true, "Task was not removed");
    }
}
