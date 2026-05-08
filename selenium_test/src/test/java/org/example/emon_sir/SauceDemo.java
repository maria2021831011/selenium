package org.example.emon_sir;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SauceDemo {
    private WebDriver driver;
    // private WebDriverWait wait;



    //    // =========================
    //    // SETUP BEFORE EACH TEST
    //    // =========================



    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");

        driver = new ChromeDriver(options);
        driver.get("https://www.saucedemo.com/");
    }


    //@BeforeEach
    //    void setUp() {
    //        driver = new ChromeDriver();
    //        driver.manage().window().maximize();
    //
    //        // Explicit wait (IMPORTANT FIX)
    //        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    //
    //        driver.get("https://www.saucedemo.com/");
    //    }
    //



      // =========================
    //    // LOGIN FUNCTION TEST
    //    // =========================
    //    /*🔹 Question 1: Login Functionality
    //Navigate to "https://www.saucedemo.com/
    //"
    //Enter username as "standard_user"
    //Enter password as "secret_sauce"
    //Click on the Login button
    //Verify that the user is redirected to the inventory page*/


    @Test
    @Order(1)
    void testSuccessfulLogin() throws InterruptedException {
        // Locate elements
        WebElement username = driver.findElement(By.id("user-name"));
        WebElement password = driver.findElement(By.id("password"));
        WebElement loginBtn = driver.findElement(By.id("login-button"));

        // Perform login
        username.sendKeys("standard_user");
        password.sendKeys("secret_sauce");
        Thread.sleep(2000);
        loginBtn.click();

        // Verify login success
        assertTrue(driver.getCurrentUrl().contains("inventory"), "User should be redirected to inventory page");
    }



    // =========================
    // INVALID LOGIN TEST
    // =========================

    /*Question 2: Invalid Login
Navigate to "https://www.saucedemo.com/
"
Enter invalid username
Enter invalid password
Click on Login
Verify that an error message is displayed*/
    void login(String username, String password) throws InterruptedException {
        driver.findElement(By.id("user-name")).sendKeys(username);
        Thread.sleep(1000);

        driver.findElement(By.id("password")).sendKeys(password);
        Thread.sleep(1000);

        driver.findElement(By.id("login-button")).click();
        Thread.sleep(1000);
    }

    @Test
    @Order(2)
    void testValidLogin() throws InterruptedException{
        login("standard_user", "secret_sauce");
        assertTrue(driver.getCurrentUrl().contains("inventory"), "Should redirect to inventory page on login");
    }
    @Test
    @Order(3)
    void testInvalidLogin() throws InterruptedException{
        login("invalid_user", "wrong_password");
        WebElement error = driver.findElement(By.cssSelector("[data-test='error']"));
        assertTrue(error.getText().contains("Username and password do not match"), "Should show error message");
    }



    // =========================
    // LOCKED USER TEST
    // =========================
    /*Question 3: Locked User Login
Navigate to "https://www.saucedemo.com/
"
Enter username as "locked_out_user"
Enter password as "secret_sauce"
Click Login
Verify that login is not successful and error message is shown*/

    @Test
    @Order(4)
    void testLockedOutUser() throws InterruptedException{
        login("locked_out_user", "secret_sauce");
        WebElement error = driver.findElement(By.cssSelector("[data-test='error']"));
        assertTrue(error.isDisplayed(), "Locked out user should see an error message");
    }



    // =========================
    // ADD TO CART TEST
    // =========================

    /*Question 4: Add Item to Cart
Navigate to "https://www.saucedemo.com/
"
Login with valid credentials
Click on the first "Add to Cart" button
Verify that the cart badge shows "1" item*/

    @Test
    @Order(5)
    void testAddToCart() throws InterruptedException{
        login("standard_user", "secret_sauce");

        List<WebElement> addButtons = driver.findElements(By.cssSelector(".btn_inventory"));
        assertTrue(addButtons.size() > 0, "Add to cart buttons should be visible");

        addButtons.get(0).click();

        WebElement cartBadge = driver.findElement(By.className("shopping_cart_badge"));
        assertEquals("1", cartBadge.getText(), "Cart badge should show 1 item added");
    }



    // =========================
    // LOGOUT TEST
    // =========================
    /*Question 5: Logout Functionality
Navigate to "https://www.saucedemo.com/
"
Login with valid credentials
Click on the menu button
Click on Logout
Verify that the user is redirected to the login page*/


    @Test
    @Order(6)
    void testLogout() throws InterruptedException{
        login("standard_user", "secret_sauce");

        driver.findElement(By.id("react-burger-menu-btn")).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.id("logout_sidebar_link")).click();
        assertEquals("https://www.saucedemo.com/", driver.getCurrentUrl(), "Should return to login page");
    }


    // =========================
    // CHECKOUT PROCESS TEST
    // =========================
    /*Question 6: Checkout Process
Navigate to "https://www.saucedemo.com/
"
Login with valid credentials
Add two items to the cart
Click on the cart icon
Click on the "Checkout" button
Enter First Name
Enter Last Name
Enter Postal Code
Click on Continue
Click on Finish
Verify that the order confirmation message is displayed*/

    void addItemToCart(int index) {
        List<WebElement> addButtons = driver.findElements(By.cssSelector(".btn_inventory"));
        assertTrue(addButtons.size() > index, "Add button should be visible for item index " + index);
        addButtons.get(index).click();
    }



    @Test
    @Order(7)
    void testCheckoutProcess() throws InterruptedException {
        // Step 1: Login
        login("standard_user", "secret_sauce");
        Thread.sleep(1000);


        // Step 2: Add two items to the cart
        addItemToCart(0); // Add first item (index 0)
        Thread.sleep(1000);

        addItemToCart(1); // Add second item (index 1)
        Thread.sleep(1000);

        // Step 3: Go to the cart
        driver.findElement(By.className("shopping_cart_link")).click();
        Thread.sleep(2000);  // Wait for the cart page to load

        // Step 4: Proceed to checkout
        driver.findElement(By.id("checkout")).click();
        Thread.sleep(2000);  // Wait for checkout page

        // Step 5: Enter customer details
        driver.findElement(By.id("first-name")).sendKeys("John");
        Thread.sleep(1000);
        driver.findElement(By.id("last-name")).sendKeys("Doe");
        Thread.sleep(1000);
        driver.findElement(By.id("postal-code")).sendKeys("12345");
        Thread.sleep(1000);

        // Step 6: Continue to next step
        driver.findElement(By.id("continue")).click();
        Thread.sleep(2000);  // Wait for review page

        // Step 7: Finish the order
        driver.findElement(By.id("finish")).click();
        Thread.sleep(2000);  // Wait for confirmation page

        // Step 8: Verify order confirmation
        String confirmationText = driver.findElement(By.className("complete-header")).getText();
        assertEquals("Thank you for your order!", confirmationText, "Order should be confirmed.");
    }



    // =========================
    // CHECKOUT VALIDATION TEST
    // =========================
    /*
    * Question 7: Checkout Validation
Navigate to "https://www.saucedemo.com/
"
Login with valid credentials
Add two items to the cart
Go to cart and click Checkout
Leave First Name field empty
Enter Last Name and Postal Code
Click Continue
Verify that an error message is displayed for missing First Name*/


    @Test
    @Order(8)
    void testIncompleteCheckoutInformation() throws InterruptedException {
        // Step 1: Login
        login("standard_user", "secret_sauce");

        // Step 2: Add two items to the cart
        Thread.sleep(1000);
        addItemToCart(0); // Add first item (index 0)
        Thread.sleep(1000);
        addItemToCart(1); // Add second item (index 1)
        Thread.sleep(1000);

        // Step 3: Go to the cart
        driver.findElement(By.className("shopping_cart_link")).click();
//        Thread.sleep(2000);  // Wait for the cart page to load
        Thread.sleep(1000);

        // Step 4: Proceed to checkout
        driver.findElement(By.id("checkout")).click();
//        Thread.sleep(2000);  // Wait for checkout page
        Thread.sleep(1000);

        // Step 5: Leave First Name empty and attempt to continue
        driver.findElement(By.id("first-name")).clear(); // Leave it blank
        Thread.sleep(1000);

        driver.findElement(By.id("last-name")).sendKeys("Doe");
        Thread.sleep(1000);

        driver.findElement(By.id("postal-code")).sendKeys("12345");
        Thread.sleep(1000);

        driver.findElement(By.id("continue")).click();
        Thread.sleep(2000);  // Wait for form submission'


        // Step 6: Verify the error message for missing first name
        WebElement errorMessage = driver.findElement(By.cssSelector("h3[data-test='error']"));
        assertTrue(errorMessage.isDisplayed(), "Error message should be displayed for missing first name.");
        assertEquals("Error: First Name is required", errorMessage.getText().trim(), "Incorrect error message for missing first name.");
    }

// * Question 8: XPath Practice
//Navigate to "https://www.saucedemo.com/
//"
//Login with valid credentials
//Locate the product "Sauce Labs Backpack" using XPath with text()
//Verify that the product is displayed
//Click on "Add to Cart" using XPath (ancestor axis)
//Verify that the cart badge shows "1*/

    @Test
    @Order(9)
    void testXPathExample() throws InterruptedException {

        /*
        1. XPath using text()
            Suppose there is no id or class, but the element has visible text.
            Example: the “Products” title after login.

            XPath:
                WebElement title = driver.findElement(By.xpath("//span[text()='Products']"));
                assertTrue(title.isDisplayed());

            Explanation
                 //span        → find span element
                 text()='Products' → whose text is "Products"


        2. XPath using contains()
        Useful when the attribute value may change dynamically.
        Example: login button.

        XPath:
            WebElement loginBtn = driver.findElement(By.xpath("//input[contains(@id,'login')]"));
            loginBtn.click();

        Explanation:
            contains(@id,'login')
            Find any element whose id contains the word “login”.


        3. XPath using hierarchy (parent → child)
        Very common in automation.
        Example: click the first product’s Add to Cart button.

        XPath:
            WebElement firstAddButton = driver.findElement(By.xpath("//div[@class='inventory_item'][1]//button"));
            firstAddButton.click();

        Explanation:
        inventory_item[1] → first product
        //button          → find button inside it



        4. Example Without ID or Class (Best for teaching)
        Find Sauce Labs Backpack product using only text.

        XPath:
        WebElement backpack = driver.findElement(By.xpath("//div[text()='Sauce Labs Backpack']"));
        assertTrue(backpack.isDisplayed());




        XPath Type	Example:
        Absolute XPath	/html/body/div/div/...
        Relative XPath	//button
        Attribute XPath	//input[@type='text']
        Contains	//button[contains(@class,'btn')]
        Text	//span[text()='Products']
        Parent/Child	//div[@class='item']//button
        Index	(//button)[1]

    */

        login("standard_user", "secret_sauce");

        WebElement product = driver.findElement(
                By.xpath("//div[text()='Sauce Labs Backpack']"));

        assertTrue(product.isDisplayed());

        WebElement addButton = driver.findElement(
                By.xpath("//div[text()='Sauce Labs Backpack']/ancestor::div[@class='inventory_item']//button"));

        addButton.click();

        WebElement cartBadge = driver.findElement(
                By.xpath("//span[@class='shopping_cart_badge']"));

        assertEquals("1", cartBadge.getText());
    }
    @AfterEach
    void tearDown() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        driver.quit();
    }


}
