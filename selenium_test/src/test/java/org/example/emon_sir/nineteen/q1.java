package org.example.emon_sir.nineteen;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class q1 {/*Perform the following automation testing using Selenium and TestNG (or Junit)
    You need to perform the following testing in a sequential order.

1. Navigate to the following website https://www.saucedemo.com/.
            2. Login with the username - "performance_glitch_user" and password - "secret_sauce"

            3. Select and navigate to the "Sauce Labs Backpack" product.
4. Click on the "Back to product" link.
5. Click on the checkout icon on the top right corner.
            6. Click on the "Checkout" button
7. Fill the following fields with some texts - "First Name", "Last Name", "Zip/Postal
    Code".
            8. Hit the "Continue" button.*/

    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://www.saucedemo.com/");
    }

    @Test
    void completeCheckoutFlow() {

        // 1. Login
        driver.findElement(By.id("user-name")).sendKeys("performance_glitch_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        // 2. Open Backpack product
        wait.until(ExpectedConditions.elementToBeClickable(
                By.id("item_4_title_link")
        )).click();

        // 3. Back to products
        wait.until(ExpectedConditions.elementToBeClickable(
                By.id("back-to-products")
        )).click();

        // 4. Open Cart
        driver.findElement(By.className("shopping_cart_link")).click();

        // 5. Click Checkout
        driver.findElement(By.id("checkout")).click();

        // 6. Fill form
        driver.findElement(By.id("first-name")).sendKeys("Nixon");
        driver.findElement(By.id("last-name")).sendKeys("Deb");
        driver.findElement(By.id("postal-code")).sendKeys("1234");

        // 7. Continue
        driver.findElement(By.id("continue")).click();

        // Optional check
        Assertions.assertTrue(driver.getCurrentUrl().contains("checkout-step-two"));
    }

    @AfterEach
    void tearDown() throws InterruptedException {
        Thread.sleep(1000);
        driver.quit();
    }
}
