package properties;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Properties {
    private static Properties ourInstance = new Properties();

    private String url;
    private String webdriverPath;

    private Properties() {
        String propertyPath = System.getProperty("propertyFilePath");
        try (BufferedReader propertyReader = new BufferedReader(new FileReader(propertyPath))) {
            String line;
            while ((line = propertyReader.readLine()) != null) {
                String[] property = line.split("=");
                switch (property[0]) {
                    case "url":
                        url = property[1];
                        break;
                    case "webdriverPath":
                        webdriverPath = property[1];
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


}
