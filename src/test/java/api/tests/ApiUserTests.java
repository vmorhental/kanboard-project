package api.tests;

import api.requestflows.UserRequestsFlow;
import com.codeborne.selenide.Configuration;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.EnvProperties;

import static utils.EnvProperties.ADMIN_USER_ID;
import static utils.EnvProperties.BASE_URL;
import static utils.RandomString.randomString;

public class ApiUserTests extends BaseApiTest{
    UserRequestsFlow userRequestsStepsOne = new UserRequestsFlow();
    UserRequestsFlow userRequestsStepsTwo = new UserRequestsFlow();
    private static final String USERNAME = "test" + randomString;
    private static final String PASSWORD = "test123!";

    @Test
    @Description("This test checks that user can be created")
    public void createUserTest() {
        Integer id = userRequestsStepsOne.createRegularUser(USERNAME,PASSWORD);
        System.out.println();
        Assert.assertTrue(Integer.valueOf(id)>0,"User was not created");
    }

    @Test
    @Description("This test checks that user can be removed")
    public void removeUserTest() {
        Integer id = userRequestsStepsTwo.createRegularUser("tester123","tester123");
        boolean isUserRemoved = userRequestsStepsTwo.deleteUser(id);
        Assert.assertEquals(isUserRemoved, true, "User was not removed");
    }
}
