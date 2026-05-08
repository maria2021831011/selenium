/*package prac1;


    import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.*;

import java.time.Duration;


public class SpicejettestNg {
        WebDriver driver;
        WebDriverWait wait;

        @BeforeClass
        public void setup() {
            driver = new ChromeDriver();
            wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            driver.manage().window().maximize();
        }

        @Test
        public void searchFlight() {

            // 1. Open website
            driver.get("https://book.spicejet.com/search.aspx");

            // 2. Select Departure City (Chennai)
            WebElement from = wait.until(ExpectedConditions.elementToBeClickable(By.id("ControlGroupSearchView_AvailabilitySearchInputSearchVieworiginStation1_CTXT")));
            from.click();
            driver.findElement(By.xpath("//a[@value='MAA']")).click(); // Chennai

            // 3. Select Arrival City (Delhi)
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@value='DEL']"))).click();

            // 4. Select Date (20 January)
            driver.findElement(By.xpath("//a[contains(@class,'ui-state-default') and text()='20']")).click();

            // 5. Select Currency (BDT)
            Select currency = new Select(driver.findElement(By.id("ControlGroupSearchView_AvailabilitySearchInputSearchView_DropDownListCurrency")));
            currency.selectByVisibleText("BDT");

            // 6. Click Search Flight
            driver.findElement(By.id("ControlGroupSearchView_AvailabilitySearchInputSearchView_ButtonSubmit")).click();
        }

        @AfterClass
        public void tearDown() {
            driver.quit();
        }
    }

*/