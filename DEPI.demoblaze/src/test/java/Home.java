import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Home {
    WebDriver driver = new ChromeDriver();

    @BeforeTest
    public void BeforeTest() {
        driver.manage().window().maximize();
        driver.navigate().to("https://www.demoblaze.com/");
    }

    @AfterTest
    public void AfterTest() {
        driver.quit();
        System.out.println("Test completed.");
    }

    @Test
    public void validate_Adding_Product_to_Cart() throws InterruptedException {
        Thread.sleep(3000);
        driver.findElement(By.xpath("//a[contains(text(),'Samsung galaxy s6')]")).click();
        Thread.sleep(4000);
        driver.findElement(By.xpath("//a[contains(text(),'Add to cart')]")).click();
        Thread.sleep(3000);

        Alert alert = driver.switchTo().alert();
        String actualAlertMessage = alert.getText();
        System.out.println("Alert Message: " + actualAlertMessage); // Debugging

        Assert.assertTrue(actualAlertMessage.contains("Product added"));
        alert.accept();
    }

   @Test
    public void validate_placing_an_Order()throws InterruptedException{
       Thread.sleep(3000);
       driver.findElement(By.xpath("//a[contains(text(),'Samsung galaxy s6')]")).click();
       Thread.sleep(4000);
       driver.findElement(By.xpath("//a[contains(text(),'Add to cart')]")).click();
       Thread.sleep(3000);
        Alert alert = driver.switchTo().alert();
        alert.accept();
        driver.findElement(By.xpath("//a[@id='cartur']")).click();
        Thread.sleep(4000);
        driver.findElement(By.xpath("//button[contains(text(),'Place Order')]")).click();
       Thread.sleep(3000);
        driver.findElement(By.xpath("//input[@id='name']")).sendKeys("sf");
        driver.findElement(By.xpath("//input[@id='country']")).sendKeys("Egypt");
        driver.findElement(By.xpath("//input[@id='city']")).sendKeys("Cairo");
        driver.findElement(By.xpath("//input[@id='card']")).sendKeys("1234567");
        driver.findElement(By.xpath("//input[@id='month']")).sendKeys("1");
        driver.findElement(By.xpath("//input[@id='year']")).sendKeys("2023");
        driver.findElement(By.xpath("//button[contains(text(),'Purchase')]")).click();
        String PurchaseDone = driver.findElement(By.xpath("//h2[contains(text(),'Thank you for your purchase!')]")).getText();

        Assert.assertTrue(PurchaseDone.contains("Thank you for your purchase!"),"order placed successfuly");

    }
}
