package api.requestflows;


import api.requestBodies.CreateProjectRequestBody;
import api.requestBodies.GeneralRequestBody;
import api.responseBodies.Result;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static api.methods.MethodsForProjectsRequests.CREATE_PROJECT;
import static utils.EnvProperties.API_TOKEN;
import static utils.EnvProperties.API_USERNAME;

public class ProjectRequestsFlow extends BasicPostRequest {
    @Step("Create project with name - {0},description - {1}, owner id - {2} parameters")
    public Integer createProject(String name, String description, int owner_id) {
        CreateProjectRequestBody createProjectRequest = new CreateProjectRequestBody().builder()
                .name(name)
                .description(description)
                .owner_id(owner_id)
                .build();

        GeneralRequestBody generalRequestBody = GeneralRequestBody.builder()
                .params(createProjectRequest)
                .method(CREATE_PROJECT)
                .build();

        Response response = postRequest(API_USERNAME, API_TOKEN, generalRequestBody);
        response.then().statusCode(200);
        Integer id = (Integer) response.as(Result.class).getResult();
        return id;
    }
}
