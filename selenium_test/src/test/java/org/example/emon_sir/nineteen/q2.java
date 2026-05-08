package org.example.emon_sir.nineteen;

import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class q2 {

    /*Perform the following automation testing using Selenium and TestNG (or Junit

1. Navigate to the following website https://book.spicejet.com/search.aspx.
2. Click on "Departure City" and select "Chennai (MMA)".
3. Select "Delhi (DEL)" as the Arrival City.
4. Select 20 January as the "DEPART DATE".
5. Select BDT as "CURRENCY".
6. Click on the flight icon to search for the flight.*/

    WebDriver driver;

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void searchFlightTest() throws InterruptedException {

        // 1. Navigate to website
        driver.get("https://book.spicejet.com/search.aspx");

        // 2. Select Departure City -> Chennai (MMA)
        driver.findElement(By.id("ctl00_mainContent_ddl_originStation1_CTXT")).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//a[contains(text(),'Chennai (MAA)')]")).click();

        // 3. Select Arrival City -> Delhi (DEL)
        driver.findElement(By.id("ctl00_mainContent_ddl_destinationStation1_CTXT")).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("(//a[contains(text(),'Delhi (DEL)')])[2]")).click();

        // 4. Select Departure Date -> 20 January
        // (Assuming current year calendar is open)
        driver.findElement(By.id("ctl00_mainContent_view_date1")).click();

        driver.findElement(By.xpath("//a[@class='ui-state-default' and text()='20']")).click();

        // 5. Select Currency -> BDT
        WebElement currencyDropdown = driver.findElement(By.id("ctl00_mainContent_DropDownListCurrency"));
        currencyDropdown.click();
        currencyDropdown.findElement(By.xpath("//option[text()='BDT']")).click();

        // 6. Click Search Flight icon/button
        driver.findElement(By.id("ctl00_mainContent_btn_FindFlights")).click();

        // Optional validation
        assertTrue(driver.getTitle().contains("SpiceJet") || driver.getCurrentUrl().contains("results"));
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}