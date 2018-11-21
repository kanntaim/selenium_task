package ngaleev;

import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FirefoxWebDriverSingleton extends FirefoxDriver{

    private final  String propertyPath = "src/resources/.property";

    private static FirefoxWebDriverSingleton ourInstance = new FirefoxWebDriverSingleton();
    private String url = null;

    public static FirefoxWebDriverSingleton getInstance() {
        return ourInstance;
    }
    public void switchTab(int number){
        ArrayList<String> tabs = new ArrayList<>(this.getWindowHandles());
        if (number>=0){
            this.switchTo().window(tabs.get(number));
        }else{
            this.switchTo().window(tabs.get(tabs.size()+number));
        }
    }

    public void returnToMainPage(){
        if(url == null){
            try(BufferedReader propertyReader= new BufferedReader(new FileReader(propertyPath))) {
                propertyReader.readLine();
                url = propertyReader.readLine();
            }
            catch (IOException e){
                e.printStackTrace();
                return;
            }
        }
        this.switchTab(0);
        this.get(url);
        this.navigate().refresh();
    }
}
