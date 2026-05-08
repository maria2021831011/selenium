package org.example.emon_sir;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class selenium {
        /*Launch Chrome browser
Open Google homepage (https://www.google.com)
Locate the search box using name = q
Enter the text "Selenium WebDriver"
Submit the search
Wait for results to load
Print the page title in console
Close the browser*/

    public static void main(String[] args) throws InterruptedException{
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.google.com");

        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("Selenium WebDriver");
        Thread.sleep(1000);

        searchBox.submit();
        Thread.sleep(1000);


        System.out.println("Title: " + driver.getTitle());

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        driver.quit();
    }
}
