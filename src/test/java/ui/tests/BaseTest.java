package ui.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import static utils.EnvProperties.BASE_URL;

public class BaseTest {
    @BeforeMethod
    public void setUp() {
        String browser = System.getProperty("browser");
        String headless = System.getProperty("headless");
        Configuration.browser = browser;
        Configuration.baseUrl = BASE_URL;
        if (headless.equals("true")) {
            Configuration.headless = true;
        }
        else Configuration.headless = false;
    }
//    @Parameters ("instanceName")
//    @BeforeTest
//    public static void setupInstance (String instanceName){
//        switch (instanceName){
//            case "kanban1":{
//
//            }
//        }
//    }

    @AfterMethod(alwaysRun = true)
    public void closeUp() {
        Selenide.closeWindow();
        Selenide.closeWebDriver();
    }

}
