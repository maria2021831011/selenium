import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.time.Duration;
public class seleniumtest {
    WebDriver driver;
    @BeforeClass
    void setup(){
driver=new ChromeDriver();
driver.manage().window().maximize();
driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }
    @AfterClass
    public  void teardown(){
        driver.quit();
    }
    @Test
    public  void testloginapp() throws InterruptedException {
       // Thread.sleep(2000);
     // WebElement username= driver.findElement(By.name("username"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement username = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.name("username"))
        );
      username.sendKeys("Admin");
      var pass=driver.findElement(By.name("password"));
        pass.sendKeys("admin123");
     // driver.findElement(By.tagName("button")).click();
        driver.findElement(By.cssSelector("button[type='submit']")).click();
//Thread.sleep(2000);
      //String actualresult=driver.findElement(By.tagName("h6")).getText();
        String actualresult = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.tagName("h6"))
        ).getText();
    String expectedres="Dashboard";
        Assert.assertEquals(actualresult,expectedres);
    }


}
