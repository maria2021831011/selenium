package org.example.emon_sir.lab2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class testlab2 {
    //Question 1: User Registration Automation
    //Go to https://automationexercise.com/
    //Click on Sign Up / Login
    //Register a new user with:
    //name
    //email
    //password
    //full details (gender, DOB, address)
    //Submit form
    //Verify account created message
    //Confirm user is logged in
    //🟢 Question 2: Product Search & Add to Cart
    //Login with valid credentials
    //Go to Products page
    //Search product: “Soft Stretch Jeans”
    //Add product to cart
    //Verify product added message
    //Go to cart page
    //Verify product is present in cart
    //🟢 Question 3: Add Product Review
    //Login
    //Search product
    //Open product details
    //Add review:
    //name
    //email
    //comment
    //Submit review
    //Verify review section is displayed



    private WebDriver driver;

    private String username = "user89312388";
    private String password = "123456";

    // =========================
    // SETUP
    // =========================

    @BeforeEach
    void setUp() throws InterruptedException {

        // Open browser in incognito mode
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");

        // Launch Chrome browser
        driver = new ChromeDriver(options);

        // Open website
        driver.get("https://automationexercise.com/login");

        Thread.sleep(2000);
    }

    @Test
    void register() {

        // Step 1: Go to Signup page
        driver.findElement(By.linkText("Signup / Login")).click();

        // Step 2: Enter basic info
        driver.findElement(By.name("name")).sendKeys(username);
        driver.findElement(By.xpath("//input[@data-qa='signup-email']"))
                .sendKeys(username + "@example.com");

        driver.findElement(By.xpath("//button[text()='Signup']")).click();

        // Step 3: Fill account details
        driver.findElement(By.id("id_gender1")).click();
        driver.findElement(By.id("password")).sendKeys(password);

        driver.findElement(By.id("days")).sendKeys("1");
        driver.findElement(By.id("months")).sendKeys("1");
        driver.findElement(By.id("years")).sendKeys("2000");

        driver.findElement(By.id("first_name")).sendKeys("Mr");
        driver.findElement(By.id("last_name")).sendKeys(username);

        driver.findElement(By.id("address1")).sendKeys("Sylhet");
        driver.findElement(By.id("country")).sendKeys("India");
        driver.findElement(By.id("state")).sendKeys("Sylhet");
        driver.findElement(By.id("city")).sendKeys("Sylhet");
        driver.findElement(By.id("zipcode")).sendKeys("3100");
        driver.findElement(By.id("mobile_number")).sendKeys("01700000000");

        // Step 4: Create account
        driver.findElement(By.xpath("//button[text()='Create Account']")).click();

        String msg = "";
        try {
            msg = driver.findElement(By.tagName("h2")).getText();
        } catch (Exception e) {
            msg = driver.findElement(By.tagName("h2")).getText();
        }

        assertEquals("ACCOUNT CREATED!", msg);

        // Step 6: Continue
        driver.findElement(By.linkText("Continue")).click();

        // Step 7: Verify logged in user
        assertTrue(driver.getPageSource().contains(username));

        // Step 8: Logout
        driver.findElement(By.linkText("Logout")).click();

        assertEquals("https://automationexercise.com/login", driver.getCurrentUrl());
    }



    @Test
    void searchProduct() throws InterruptedException {

        // Step 1: Login first
        login();

        // Step 2: Go to Products page
        driver.findElement(By.xpath("//a[contains(text(),'Products')]")).click();
        Thread.sleep(2000);

        assertEquals("https://automationexercise.com/products",
                driver.getCurrentUrl());

        // Step 3: Search product
        driver.findElement(By.id("search_product"))
                .sendKeys("Soft Stretch Jeans");

        driver.findElement(By.id("submit_search")).click();
        Thread.sleep(2000);

        // Step 4: Add to cart
        driver.findElement(By.cssSelector(".add-to-cart")).click();
        Thread.sleep(2000);

        // Step 5: Verify popup
        assertTrue(driver.findElement(By.className("modal-title"))
                .getText().contains("Added!"));

        // Close popup
        driver.findElement(By.className("btn-success")).click();
        Thread.sleep(2000);

        // Step 6: Go to cart
        driver.findElement(By.xpath("//a[contains(text(),'Cart')]")).click();
        Thread.sleep(2000);

        // Step 7: Verify product
        assertTrue(driver.getPageSource()
                .contains("Soft Stretch Jeans"));
    }
    void login() throws InterruptedException {



        driver.findElement(By.name("email"))
                .sendKeys(username + "@example.com");

        driver.findElement(By.name("password"))
                .sendKeys(password);

        driver.findElement(By.cssSelector("button[type='submit']")).click();

        Thread.sleep(2000);
    }
    @Test
    void addReview() throws InterruptedException {

        // Step 1: Login
        login();

        // Step 2: Go to Products page
        driver.findElement(By.xpath("//a[contains(text(),'Products')]")).click();
        Thread.sleep(2000);

        assertEquals("https://automationexercise.com/products",
                driver.getCurrentUrl());

        // Step 3: Search product
        driver.findElement(By.id("search_product"))
                .sendKeys("Soft Stretch Jeans");

        driver.findElement(By.id("submit_search")).click();
        Thread.sleep(2000);

        // Step 4: Open product details
        driver.findElement(By.xpath("//a[contains(text(),'View Product')]")).click();
        Thread.sleep(2000);

        // Step 5: Fill review form
        driver.findElement(By.id("name")).sendKeys(username);

        driver.findElement(By.id("email"))
                .sendKeys(username + "@example.com");

        driver.findElement(By.id("review"))
                .sendKeys("Nice Jeans, but overpriced!!");

        // Step 6: Submit review
        driver.findElement(By.id("button-review")).click();

        Thread.sleep(2000);

        // Step 7: Verify review success (simple check)
        assertTrue(driver.getPageSource()
                .contains("Thank you for your review"));
    }


    @AfterEach
    void end() throws  InterruptedException{
        Thread.sleep(2000);
        driver.quit();
    }
}
