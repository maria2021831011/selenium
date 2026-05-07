package base;

import com.saucedemo.pages.loginpage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class basetest {

        protected WebDriver driver;
        protected basepage basePage;
        protected loginpage loginPage;
        private String url = "https://www.saucedemo.com";

        @BeforeClass
        public void setUp() {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get(url);
            basePage = new basepage();
            basePage.setDriver(driver);
            loginPage = new loginpage();
        }

        @AfterClass
        public void tearDown() {
            driver.quit();
        }
    }

