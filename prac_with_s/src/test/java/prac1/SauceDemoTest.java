/*
package prac1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.*;

import java.time.Duration;

public class SauceDemoTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void completeCheckoutFlow() {

        driver.get("https://www.saucedemo.com/");

        // Login
        driver.findElement(By.id("user-name")).sendKeys("performance_glitch_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        // Wait for inventory page
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("inventory_list")));

        // Add product
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();

        // Go to cart first
        driver.findElement(By.className("shopping_cart_link")).click();

// Wait for cart page
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("continue-shopping")));

// Click continue shopping
        driver.findElement(By.id("continue-shopping")).click();

// Wait for products page
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("inventory_list")));

// AGAIN go to cart (THIS LINE IS MUST)
        driver.findElement(By.className("shopping_cart_link")).click();

// Now wait for checkout
        wait.until(ExpectedConditions.elementToBeClickable(By.id("checkout")));

// Now click checkout
        driver.findElement(By.id("checkout")).click();
        // Fill form
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("first-name")));
        driver.findElement(By.id("first-name")).sendKeys("John");
        driver.findElement(By.id("last-name")).sendKeys("Doe");
        driver.findElement(By.id("postal-code")).sendKeys("12345");

        driver.findElement(By.id("continue")).click();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}

*/