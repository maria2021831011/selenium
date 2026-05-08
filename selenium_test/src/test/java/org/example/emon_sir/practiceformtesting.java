package org.example.emon_sir;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

public class practiceformtesting {

    //Open the form:
    //https://demoqa.com/automation-practice-form
    //Fill up the form:
    //First Name
    //Last Name
    //Email
    //Gender
    //Scroll down and click Submit button
    //Validate form submission:
    //Check if form is submitted successfully OR
    //Detect invalid fields using input:invalid
    //Print invalid field IDs if submission fails


    WebDriver driver;
    String url = "https://demoqa.com/automation-practice-form";

    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        driver = new ChromeDriver(options);
        driver.get(url);
    }


        @Test
        void submitForm() throws InterruptedException {

            // Step 1: Fill form
            driver.findElement(By.id("firstName")).sendKeys("Nixon");
            driver.findElement(By.id("lastName")).sendKeys("Deb");
            driver.findElement(By.id("userEmail")).sendKeys("nixon@example.com");

            // Step 2: Select gender
            driver.findElement(By.cssSelector("label[for='gender-radio-1']")).click();

            // Step 3: Scroll to submit button
            WebElement submitBtn = driver.findElement(By.id("submit"));
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView(true);", submitBtn
            );

            Thread.sleep(1000);

            // Step 4: Submit form
            submitBtn.click();

            Thread.sleep(2000);

            // Step 5: Check invalid fields
            List<WebElement> invalidFields =
                    driver.findElements(By.cssSelector("input:invalid"));

            if (invalidFields.size() == 0) {
                System.out.println("Form submitted successfully");
            } else {
                System.out.println("Form has errors:");
                for (WebElement field : invalidFields) {
                    System.out.println(field.getAttribute("id"));
                }
            }
        }

        @AfterEach
        void tearDown() throws InterruptedException {
            Thread.sleep(1000);
            driver.quit();
        }
    }
