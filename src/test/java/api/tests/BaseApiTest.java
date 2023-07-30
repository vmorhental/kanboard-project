package api.tests;

import org.testng.annotations.BeforeMethod;
import utils.EnvProperties;

public class BaseApiTest {
    @BeforeMethod
    public void setUpAPI() {
        EnvProperties.setupInstance(System.getProperty("instanceName", " "));
    }

}
