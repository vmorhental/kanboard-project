package api.requestflows;

import api.requestBodies.GeneralRequestBody;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static utils.EnvProperties.API_URL;

public class BasicPostRequest {
        public Response postRequest(String username, String token, GeneralRequestBody generalRequestBody) {
            return RestAssured.given()
                    .auth().basic(username, token)
                    .body(generalRequestBody)
                    .when()
                    .post(API_URL);
        }
}
