package api.requestflows;


import api.objects.ProjectId;
import api.requestBodies.CreateProjectRequestBody;
import api.requestBodies.GeneralRequestBody;
import api.requestBodies.LinkProjectToUserRequestBody;
import api.responseBodies.Result;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static api.methods.MethodsForProjectsRequests.*;
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
    @Step("link user {0} to project {1}")
    public void linkProjectToCustomer(Integer userId, Integer projectId){
        LinkProjectToUserRequestBody body = new LinkProjectToUserRequestBody().builder()
                .project_id(projectId)
                .user_id(userId)
                .build();

        GeneralRequestBody finalBody = GeneralRequestBody.builder()
                .params(body)
                .method(LINK_PROJECT_TO_USER)
                .build();

        Response response = postRequest(API_USERNAME, API_TOKEN, finalBody);
        response.then().statusCode(200);
    }
    @Step("We remove project with id - {0}")
    public boolean removeProject(Integer removeProjectId){
        GeneralRequestBody body = GeneralRequestBody.builder()
                .params(new ProjectId(removeProjectId))
                .method(REMOVE_PROJECT)
                .build();
        Response response = postRequest(API_USERNAME,API_TOKEN,body);
        response.then().statusCode(200);
        return (boolean) response.as(Result.class).getResult();
    }
}
