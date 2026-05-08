package prac1;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class prac3 {

    private WebDriver driver;
    private WebDriverWait wait;

    // =========================
    // SETUP BEFORE EACH TEST
    // =========================
    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Explicit wait (IMPORTANT FIX)
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://www.saucedemo.com/");
    }

    // =========================
    // LOGIN FUNCTION TEST
    // =========================
    /*🔹 Question 1: Login Functionality
Navigate to "https://www.saucedemo.com/
"
Enter username as "standard_user"
Enter password as "secret_sauce"
Click on the Login button
Verify that the user is redirected to the inventory page*/
    @Test
    @Order(1)
    void testSuccessfulLogin() {

        // Enter username
        driver.findElement(By.id("user-name"))
                .sendKeys("standard_user");

        // Enter password
        driver.findElement(By.id("password"))
                .sendKeys("secret_sauce");

        // Click login button
        driver.findElement(By.id("login-button"))
                .click();

        // Verify user redirected to inventory page
        assertTrue(driver.getCurrentUrl().contains("inventory"),
                "User should be redirected to inventory page");
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
    @Test
    @Order(2)
    void testInvalidLogin() {

        login("invalid_user", "wrong_password");

        // Capture error message
        WebElement error = driver.findElement(By.cssSelector("[data-test='error']"));

        // Verify error message
        assertTrue(error.getText().contains("Username and password do not match"),
                "Should show error message");
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
    @Order(3)
    void testLockedOutUser() {

        login("locked_out_user", "secret_sauce");

        WebElement error = driver.findElement(By.cssSelector("[data-test='error']"));

        assertTrue(error.isDisplayed(),
                "Locked out user should see error message");
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
    @Order(4)
    void testAddToCart() {

        login("standard_user", "secret_sauce");

        // Find all add to cart buttons
        List<WebElement> addButtons = driver.findElements(By.cssSelector(".btn_inventory"));

        // Click first item
        addButtons.get(0).click();

        // Validate cart badge
        WebElement cartBadge = driver.findElement(By.className("shopping_cart_badge"));

        assertEquals("1", cartBadge.getText(),
                "Cart should contain 1 item");
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
    @Order(5)
    void testLogout() {

        login("standard_user", "secret_sauce");

        // Open menu
        wait.until(ExpectedConditions.elementToBeClickable(By.id("react-burger-menu-btn"))).click();

        // Click logout
        wait.until(ExpectedConditions.elementToBeClickable(By.id("logout_sidebar_link"))).click();

        // Verify back to login page
        assertEquals("https://www.saucedemo.com/",
                driver.getCurrentUrl(),
                "Should return to login page");
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
    @Test
    @Order(6)
    void testCheckoutProcess() {

        login("standard_user", "secret_sauce");

        // Add items
        addItemToCart(0);
        addItemToCart(1);

        // Go to cart
        driver.findElement(By.className("shopping_cart_link")).click();

        // Click checkout (WAIT FIXED HERE)
        wait.until(ExpectedConditions.elementToBeClickable(By.id("checkout"))).click();

        // Fill checkout form
        driver.findElement(By.id("first-name")).sendKeys("John");
        driver.findElement(By.id("last-name")).sendKeys("Doe");
        driver.findElement(By.id("postal-code")).sendKeys("12345");

        // Continue
        driver.findElement(By.id("continue")).click();

        // Finish order
        driver.findElement(By.id("finish")).click();

        // Validate confirmation message
        String message = driver.findElement(By.className("complete-header")).getText();

        assertEquals("Thank you for your order!", message);
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
    @Order(7)
    void testIncompleteCheckoutInformation() {

        login("standard_user", "secret_sauce");

        addItemToCart(0);
        addItemToCart(1);

        driver.findElement(By.className("shopping_cart_link")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("checkout"))).click();

        // Leave first name empty
        driver.findElement(By.id("last-name")).sendKeys("Doe");
        driver.findElement(By.id("postal-code")).sendKeys("12345");

        driver.findElement(By.id("continue")).click();

        // Error message validation
        WebElement error = driver.findElement(By.cssSelector("h3[data-test='error']"));

        assertTrue(error.isDisplayed());
        assertEquals("Error: First Name is required",
                error.getText().trim());
    }

    // =========================
    // XPATH PRACTICE TEST
    // =========================
    /*
    * Question 8: XPath Practice
Navigate to "https://www.saucedemo.com/
"
Login with valid credentials
Locate the product "Sauce Labs Backpack" using XPath with text()
Verify that the product is displayed
Click on "Add to Cart" using XPath (ancestor axis)
Verify that the cart badge shows "1*/
    @Test
    @Order(8)
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
    void testXPathExample() {

        login("standard_user", "secret_sauce");

        // Find product using text XPath
        WebElement product = driver.findElement(
                By.xpath("//div[text()='Sauce Labs Backpack']"));

        assertTrue(product.isDisplayed());

        // Add to cart using ancestor XPath
        WebElement addButton = driver.findElement(
                By.xpath("//div[text()='Sauce Labs Backpack']/ancestor::div[@class='inventory_item']//button"));

        addButton.click();

        // Validate cart badge
        WebElement cartBadge = driver.findElement(
                By.xpath("//span[@class='shopping_cart_badge']"));

        assertEquals("1", cartBadge.getText());
    }

    // =========================
    // REUSABLE LOGIN METHOD
    // =========================
    void login(String username, String password) {

        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();
    }

    // =========================
    // REUSABLE ADD TO CART METHOD
    // =========================
    void addItemToCart(int index) {

        List<WebElement> buttons = driver.findElements(By.cssSelector(".btn_inventory"));

        assertTrue(buttons.size() > index);

        buttons.get(index).click();
    }

    // =========================
    // CLEANUP AFTER EACH TEST
    // =========================
    @AfterEach
    void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}