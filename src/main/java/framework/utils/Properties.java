package framework.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Properties {
    private static Properties ourInstance = new Properties();

    private String url;
    private String webdriverPath;
    private String webdriverName;

    private Properties() {
        String propertiesPath = System.getProperty("propertiesFilePath");
        try (BufferedReader propertyReader = new BufferedReader(new FileReader(propertiesPath))) {
            String line;
            while ((line = propertyReader.readLine()) != null) {
                String[] property = line.split("=");
                String propertyName = property[0];
                String propertyValue = property[1];
                switch (propertyName) {
                    case "url":
                        url = propertyValue;
                        break;
                    case "webdriverPath":
                        webdriverPath = propertyValue;
                        break;
                    case "webdriverName":
                        webdriverName = propertyValue;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Properties getInstance() {
        return ourInstance;
    }

    public String getUrl() {
        return url;
    }

    public String getWebdriverPath() {
        return webdriverPath;
    }

    public String getWebdriverName(){
        return webdriverName;
    }


}
