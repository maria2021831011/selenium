package utilities;

import base.basepage;
import org.openqa.selenium.WebDriver;

public class Utility {
    public static WebDriver driver;

    public static void setUtilityDriver() {
        driver = basepage.driver;
    }

}
