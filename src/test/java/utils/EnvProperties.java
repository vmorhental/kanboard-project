package utils;


import org.testng.annotations.Parameters;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static java.util.Objects.isNull;

public class EnvProperties {
    public static final Integer ADMIN_USER_ID = 36;
    private static final String PATH_TO_RESOURCE = "src/test/resources/";
    private static final String PROPERTIES_FILE_NAME = "env.properties";
    private static final String PROPERTIES_FILE_NAME_KANBAN1 = "envkanban1.properties";
    public static String BASE_URL;
    public static String API_USERNAME;
    public static String API_TOKEN;
    public static String API_URL;

    public static String propertyValue(String pathToFile, String propertyFileName, String propertyName) {
        String systemProperty = System.getProperty(propertyName);
        return !isNull(systemProperty) ? systemProperty : getPropertyValue(pathToFile, propertyFileName, propertyName);
    }

    private static String getPropertyValue(String pathToFile, String fileName, String propertyName) {
        Properties prop = new Properties();
        try (FileInputStream fis = new FileInputStream(pathToFile + fileName)) {
            prop.load(fis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return prop.getProperty(propertyName);
    }

    @Parameters("instanceName")
    public static final void setupInstance(String instanceName) {
        switch (instanceName) {
            case "kanban1": {
                API_URL = propertyValue(PATH_TO_RESOURCE, PROPERTIES_FILE_NAME_KANBAN1, "apiUrlKanban1");
                API_USERNAME = propertyValue(PATH_TO_RESOURCE, PROPERTIES_FILE_NAME_KANBAN1, "usernameKanban1");
                API_TOKEN = propertyValue(PATH_TO_RESOURCE, PROPERTIES_FILE_NAME_KANBAN1, "tokenKanban1");
                BASE_URL = propertyValue(PATH_TO_RESOURCE, PROPERTIES_FILE_NAME_KANBAN1, "baseUrlKanban1");
                break;
            }
            default: {
                BASE_URL = propertyValue(PATH_TO_RESOURCE, PROPERTIES_FILE_NAME, "baseUrl");
                API_USERNAME = propertyValue(PATH_TO_RESOURCE, PROPERTIES_FILE_NAME, "username");
                API_TOKEN = propertyValue(PATH_TO_RESOURCE, PROPERTIES_FILE_NAME, "token");
                API_URL = propertyValue(PATH_TO_RESOURCE, PROPERTIES_FILE_NAME, "apiUrl");
            }
        }
    }

}
