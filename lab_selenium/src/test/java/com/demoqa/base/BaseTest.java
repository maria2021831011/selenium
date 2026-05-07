package com.demoqa.base;

import com.demoqa.pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class BaseTest {

    protected WebDriver driver;
    protected HomePage homePage;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @BeforeMethod
    public void openApp() {
        driver.get("https://demoqa.com/");

        homePage = new HomePage(driver);  // 🔥 DRIVER PASS IMPORTANT
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}