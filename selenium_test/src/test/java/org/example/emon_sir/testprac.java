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

import static org.junit.jupiter.api.Assertions.assertTrue;

public class testprac {
    //Open the page:
    //https://demoqa.com/text-box
    //Perform 3 test cases:
    //Submit only Name and verify output
    //Submit only Email and verify output
    //Submit invalid email and verify error class
    //Scroll to Submit button before clicking
    //Validate results using:
    //Output text (#name, #email)
    //OR field validation (field-error class)



        WebDriver driver;
        String url = "https://demoqa.com/text-box";

        @BeforeEach
        void setUp() {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--incognito");
            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
            driver.get(url);
        }

        // =========================
        // Helper: Submit button
        // =========================
        void submit() {

            WebElement submitBtn = driver.findElement(By.id("submit"));

            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].scrollIntoView(true);", submitBtn);

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].click();", submitBtn);
        }

        // =========================
        // Test 1: Only Name
        // =========================
        @Test
        void onlyName() {

            String username = "Nixon";

            driver.findElement(By.id("userName"))
                    .sendKeys(username);

            submit();

            String result = driver.findElement(By.id("name")).getText();
            assertTrue(result.contains(username));
        }

        // =========================
        // Test 2: Only Email
        // =========================
        @Test
        void onlyEmail() {

            String email = "abcd@example.com";

            driver.findElement(By.id("userEmail"))
                    .sendKeys(email);

            submit();

            String result = driver.findElement(By.id("email")).getText();
            assertTrue(result.contains(email));
        }

        // =========================
        // Test 3: Invalid Email
        // =========================
        @Test
        void invalidEmail() {

            WebElement emailInput = driver.findElement(By.id("userEmail"));

            emailInput.sendKeys("wrongemail.com");

            submit();

            assertTrue(
                    emailInput.getDomAttribute("class").contains("field-error")
            );
        }

        // =========================
        // Cleanup
        // =========================
        @AfterEach
        void tearDown() {

            if (driver != null) {
                driver.quit();
            }
        }
    }
