package org.example.emon_sir.lab1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class test {

    //Question 1: Selenium Web Automation
    //Site: www.demoblaze.com
    //User Registration
    //Click on the Sign up link.
    //Register with a unique username and any password.
    //Handle the signup success alert.
    //Login
    //Log in with the same credentials used in the signup process.
    //Wait for and confirm successful login (e.g., by checking the presence of “Welcome [username]”).
    //Add Products to Cart
    //Add the following products to the cart:
    //3 laptops of the same product (e.g., “Sony vaio i5”).
    //2 monitors of different products (e.g., “ASUS Full HD”, “Apple monitor 24”).
    //3 phones of different products (e.g., “Samsung galaxy s6”, “Nokia lumia 1520”, “HTC One M9”).
    //After each add, handle the “Product added” alert.
    //Return to home after adding each item.
    //Cart and Checkout
    //Go to the Cart page.
    //Verify that the selected 8 products are listed.
    //Click on Place Order.
    //Fill in all required fields in the order form with dummy data.
    //Click Purchase.
    //Save the confirmation message details and print it to the console.
    //Send a Contact Message
    //Click on the Contact tab.
    //Fill in the contact form with a name, email, and a short message.

        // WebDriver for browser automation
        private WebDriver driver;

    private String username = "user83123";
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
            driver.get("https://www.demoblaze.com");

            Thread.sleep(2000);
        }

        // =========================
        // USER REGISTRATION TEST
        // =========================

    /*
        Question:
        Click Sign up
        Register with unique username
        Handle success alert
     */

        @Test
        void testUserRegistrationAndLogin() throws InterruptedException {

            // Click Sign up button
            driver.findElement(By.id("signin2")).click();

            Thread.sleep(2000);

            // Create unique username
            //username = "user" + System.currentTimeMillis();

            // Set password
            //password = "123456";

            // Enter username
            driver.findElement(By.id("sign-username"))
                    .sendKeys(username);

            // Enter password
            driver.findElement(By.id("sign-password"))
                    .sendKeys(password);

            // Click Sign up submit button
            driver.findElement(
                            By.xpath("//button[text()='Sign up']"))
                    .click();

            Thread.sleep(2000);

            // Switch to alert popup
            Alert alert = driver.switchTo().alert();

            // Verify success message
            assertEquals("Sign up successful.", alert.getText());

            // Accept alert
            alert.accept();

            Thread.sleep(2000);

            // =========================
            // LOGIN PART
            // =========================

}
    // =========================
    // LOGIN PART


        // assume এগুলো signup time এ set করা


        // reusable login method
        void login(String username, String password) throws InterruptedException {

            // click login button/link
            driver.findElement(By.id("login2")).click();
            Thread.sleep(2000);

            // enter username
            driver.findElement(By.id("loginusername"))
                    .sendKeys(username);

            // enter password
            driver.findElement(By.id("loginpassword"))
                    .sendKeys(password);

            // click login submit
            driver.findElement(By.xpath("//button[text()='Log in']"))
                    .click();

            Thread.sleep(3000);
        }

        @Test
        void testLoginWithSignupCredentials() throws InterruptedException {

            // Step 1: login using same signup credentials
            login(username, password);

            // Step 2: verify successful login message
            WebElement welcomeText =
                    driver.findElement(By.id("nameofuser"));

            // Step 3: check visibility
            assertTrue(welcomeText.isDisplayed());

            // Step 4: check correct username appears
            assertTrue(
                    welcomeText.getText().contains(username),
                    "Login failed or username not shown"
            );
        }




    @Test
    void addProductsToCart() throws InterruptedException {

        // =========================
        // LOGIN
        // =========================
        login(username,password);

        // reusable method idea: add product
        // but here written simple for exam clarity

        // =========================
        // LAPTOPS (Sony vaio i5 × 3)
        // =========================
        for (int i = 0; i < 3; i++) {

            driver.findElement(By.linkText("Laptops")).click();
            Thread.sleep(1000);

            driver.findElement(By.linkText("Sony vaio i5")).click();
            Thread.sleep(2000);

            driver.findElement(By.linkText("Add to cart")).click();
            Thread.sleep(2000);

            // alert handle
            driver.switchTo().alert().accept();
            Thread.sleep(1000);

            // go home
            driver.findElement(By.id("nava")).click();
            Thread.sleep(2000);
        }

        // =========================
        // MONITORS (2 different)
        // =========================
        String[] monitors = {"ASUS Full HD", "Apple monitor 24"};

        for (String monitor : monitors) {

            driver.findElement(By.linkText("Monitors")).click();
            Thread.sleep(1000);

            driver.findElement(By.linkText(monitor)).click();
            Thread.sleep(2000);

            driver.findElement(By.linkText("Add to cart")).click();
            Thread.sleep(2000);

            driver.switchTo().alert().accept();
            Thread.sleep(1000);

            driver.findElement(By.id("nava")).click();
            Thread.sleep(2000);
        }

        // =========================
        // PHONES (3 different)
        // =========================
        String[] phones = {
                "Samsung galaxy s6",
                "Nokia lumia 1520",
                "HTC One M9"
        };

        for (String phone : phones) {

            driver.findElement(By.linkText("Phones")).click();
            Thread.sleep(1000);

            driver.findElement(By.linkText(phone)).click();
            Thread.sleep(2000);

            driver.findElement(By.linkText("Add to cart")).click();
            Thread.sleep(2000);

            driver.switchTo().alert().accept();
            Thread.sleep(1000);

            driver.findElement(By.id("nava")).click();
            Thread.sleep(2000);
        }
    }



    @Test
    void checkOut() throws InterruptedException {

        // Step 1: Login
        login(username,password);

        // Step 2: Go to Cart
        driver.findElement(By.id("cartur")).click();
        Thread.sleep(2000);

        // Step 3: Click Place Order
        driver.findElement(By.xpath("//button[text()='Place Order']")).click();
        Thread.sleep(1000);

        // Step 4: Fill form (dummy data)
        driver.findElement(By.id("name")).sendKeys("John");
        driver.findElement(By.id("country")).sendKeys("Bangladesh");
        driver.findElement(By.id("city")).sendKeys("Dhaka");
        driver.findElement(By.id("card")).sendKeys("123456");
        driver.findElement(By.id("month")).sendKeys("12");
        driver.findElement(By.id("year")).sendKeys("2026");

        // Step 5: Click Purchase
        driver.findElement(By.xpath("//button[text()='Purchase']")).click();
        Thread.sleep(2000);

        // Step 6: Verify success message
        String msg = driver.findElement(By.cssSelector(".sweet-alert h2")).getText();

        System.out.println(msg);

        assertEquals("Thank you for your purchase!", msg);
    }

    @Test
    void sendContactMessage() throws InterruptedException {

        // =====================
        // Step 1: Click Contact tab
        // =====================
        driver.findElement(By.xpath("//a[text()='Contact']")).click();
        Thread.sleep(2000);

        // =====================
        // Step 2: Fill Contact form
        // =====================
        driver.findElement(By.id("recipient-email")).sendKeys("test@gmail.com");
        driver.findElement(By.id("recipient-name")).sendKeys("John Doe");
        driver.findElement(By.id("message-text")).sendKeys("Hello, this is a test message");

        // =====================
        // Step 3: Click Send message
        // =====================
        driver.findElement(By.xpath("//button[text()='Send message']")).click();
        Thread.sleep(2000);

        // =====================
        // Step 4: Handle Alert
        // =====================
        String alertText = driver.switchTo().alert().getText();
        System.out.println("Alert Message: " + alertText);

        driver.switchTo().alert().accept();
    }
}


//public class DemoBlaze {
//    private WebDriver driver;
//    private static String UserName = "lojexejoke";
//    private static String Password = "lojexejoke";
//
//    @BeforeEach
//    void setUp() {
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--incognito");
//        driver = new ChromeDriver(options);
//        driver.get("https://www.demoblaze.com");
//        delay(1000);
//    }
//
//    @Test
//    // User Registration
//    void signup() {
//        driver.findElement(By.id("signin2")).click();
//        delay(500);
//        driver.findElement(By.id("sign-username")).sendKeys(UserName);
//        driver.findElement(By.id("sign-password")).sendKeys(Password);
//        driver.findElement(By.xpath("//*[@id=\"signInModal\"]/div/div/div[3]/button[2]")).click();
//        delay(2000);
//        assertTrue(driver.switchTo().alert().getText().contains("Sign up successful"));
//        driver.switchTo().alert().accept();
//    }
//
//    @Test
//    // Login
//    void loginTest() {
//        login();
//        assertTrue(driver.findElement(By.id("nameofuser")).getText().contains(UserName));
//    }
//
//    @Test
//    // Add Products to Cart only the first item here
//    void addProduct() {
//        login();
//        driver.findElement(By.xpath("/html/body/div[5]/div/div[1]/div/a[3]")).click();
//        delay(1000);
//        WebElement product1 = driver.findElement(By.xpath("/html/body/div[5]/div/div[2]/div/div[1]/div/div/h4/a"));
//        assertEquals("Sony vaio i5", product1.getText());
//        product1.click();
//        delay(2000);
//        for (int i = 0; i < 3; i++) {
//            driver.findElement(By.xpath("//*[@id=\"tbodyid\"]/div[2]/div/a")).click();
//            delay(1500);
//            assertTrue(driver.switchTo().alert().getText().contains("Product added"));
//            driver.switchTo().alert().accept();
//        }
//    }
//
//    @Test
//    // Cart and Checkout
//    void checkOut() {
//        login();
//        driver.findElement(By.id("cartur")).click();
//        delay(2000);
//        driver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div/div[2]/button")).click();
//        delay(500);
//        driver.findElement(By.id("name")).sendKeys(UserName);
//        driver.findElement(By.id("country")).sendKeys(UserName);
//        driver.findElement(By.id("city")).sendKeys(UserName);
//        driver.findElement(By.id("card")).sendKeys(UserName);
//        driver.findElement(By.id("month")).sendKeys(UserName);
//        driver.findElement(By.id("year")).sendKeys(UserName);
//        driver.findElement(By.xpath("//*[@id=\"orderModal\"]/div/div/div[3]/button[2]")).click();
//        delay(4000);
//        assertEquals("Thank you for your purchase!",
//                driver.findElement(By.cssSelector(".sweet-alert.visible h2")).getText());
//    }
//
//    @Test
//    // Send a Contact Message
//    void contact() {
//        driver.findElement(By.xpath("/html/body/nav/div[1]/ul/li[2]/a")).click();
//        delay(1000);
//        driver.findElement(By.id("recipient-email")).sendKeys(UserName + "@example.com");
//        driver.findElement(By.id("recipient-name")).sendKeys(UserName);
//        driver.findElement(By.id("message-text")).sendKeys("Add uid for the buttons please");
//        driver.findElement(By.xpath("//*[@id=\"exampleModal\"]/div/div/div[3]/button[2]")).click();
//        delay(300);
//        assertTrue(driver.switchTo().alert().getText().contains("Thanks for the message"));
//    }
//
//    void login() {
//        driver.findElement(By.id("login2")).click();
//        delay(500);
//        driver.findElement(By.id("loginusername")).sendKeys(UserName);
//        driver.findElement(By.id("loginpassword")).sendKeys(Password);
//        driver.findElement(By.xpath("//*[@id=\"logInModal\"]/div/div/div[3]/button[2]")).click();
//        delay(3000);
//    }
//
//    void delay(int mili) {
//        try {
//            Thread.sleep(mili);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @AfterEach
//    void end() {
//        delay(1000);
//        driver.quit();
//    }
//}

