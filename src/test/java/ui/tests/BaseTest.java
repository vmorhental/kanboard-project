package ui.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static utils.EnvProperties.BASE_URL;

public class BaseTest {
    @BeforeMethod
    public void setUp() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = BASE_URL;
    }

    @AfterMethod(alwaysRun = true)
    public void closeUp() {
        Selenide.closeWindow();
        Selenide.closeWebDriver();
    }

}
