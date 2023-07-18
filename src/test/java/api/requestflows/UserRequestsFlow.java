package api.requestflows;

import api.objects.UserId;
import api.requestBodies.CreateUserRequestBody;
import api.requestBodies.GeneralRequestBody;
import api.responseBodies.Result;
import io.restassured.response.Response;
import org.openqa.selenium.remote.ProtocolHandshake;

import static api.enums.UserRoles.USER;
import static api.methods.MethodsForUserRequests.*;
import static utils.EnvProperties.API_TOKEN;
import static utils.EnvProperties.API_USERNAME;
import static utils.RandomString.randomString;

public class UserRequestsFlow extends BasicPostRequest {
    public String createRegularUser(String username, String password) {
        CreateUserRequestBody createUserRequestBody = new CreateUserRequestBody().builder()
                .name(username)
                .email(username + "@gmail.com")
                .password(password)
                .username(username)
                .role(USER.getRole())
                .build();

        GeneralRequestBody requestBody = GeneralRequestBody.builder()
                .method(CREATE_USER)
                .params(createUserRequestBody)
                .build();

        Response response = postRequest(API_USERNAME, API_TOKEN, requestBody);
        response.then().statusCode(200);
        Result result = response.as(Result.class);
        return response.as(Result.class).getResult().toString();
    }

    public Integer getUserById(String id) {
        GeneralRequestBody getUser = GeneralRequestBody.builder()
                .params(new UserId(Integer.valueOf(id)))
                .method(GET_USER)
                .build();

        Response response = postRequest(API_USERNAME, API_TOKEN, getUser);
        response.then().statusCode(200);

        Integer userId = (Integer) response.as(Result.class).getResult();
        return userId;
    }

    public boolean deleteUser (String id){
        GeneralRequestBody deleteUser = GeneralRequestBody.builder()
                .params(new UserId(Integer.valueOf(id)))
                .method(REMOVE_USER)
                .build();

        Response response = postRequest(API_USERNAME,API_TOKEN,deleteUser);
        return (boolean) response.as(Result.class).getResult();
    }
}
