package api.requestflows;

import api.objects.TaskId;
import api.requestBodies.CreateTaskRequestBody;
import api.requestBodies.GeneralRequestBody;
import api.responseBodies.Result;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static api.methods.MethodsForTasksRequests.CREATE_TASK;
import static api.methods.MethodsForTasksRequests.REMOVE_TASK;
import static utils.EnvProperties.*;

public class TaskRequestFlow extends BasicPostRequest {
    ProjectRequestsFlow projectRequestsFlow = new ProjectRequestsFlow();
@Step("We create task for created project")
    public Integer createTaskForCreatedProject() {
        Integer projectIdforTask;
        projectIdforTask = projectRequestsFlow.createProject("Valerii", "Testdescription", ADMIN_USER_ID);
        CreateTaskRequestBody body = new CreateTaskRequestBody().builder()
                .title("test")
                .project_id(projectIdforTask)
                .build();

        GeneralRequestBody finalBody = GeneralRequestBody.builder()
                .method(CREATE_TASK)
                .params(body)
                .build();

        Response response = postRequest(API_USERNAME, API_TOKEN, finalBody);
        response.then().statusCode(200);
        return (Integer) response.as(Result.class).getResult();
    }
    @Step("We create task for given project id {0}, with title {1}")
    public Integer createTaskForCreatedProject(Integer projectID, String taskTitle) {
        CreateTaskRequestBody body = new CreateTaskRequestBody().builder()
                .title(taskTitle)
                .project_id(projectID)
                .build();

        GeneralRequestBody finalBody = GeneralRequestBody.builder()
                .method(CREATE_TASK)
                .params(body)
                .build();

        Response response = postRequest(API_USERNAME, API_TOKEN, finalBody);
        response.then().statusCode(200);
        return (Integer) response.as(Result.class).getResult();
    }
    @Step("We remove task with id {0}")
    public boolean deleteTask (Integer taskId){
        GeneralRequestBody removeTaskBody = GeneralRequestBody.builder()
                .method(REMOVE_TASK)
                .params(new TaskId(taskId))
                .build();

        Response response = postRequest(API_USERNAME, API_TOKEN, removeTaskBody);
        response.then().statusCode(200);
        return (boolean) response.as(Result.class).getResult();
    }
}
