import org.openqa.selenium.Alert;
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

public class Home {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://www.demoblaze.com/");
        System.out.println("🚀 Browser launched and navigated to DemoBlaze.");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
        System.out.println("✅ Test completed. Browser closed.");
    }

    @Test
    public void validate_Adding_Product_to_Cart() {
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Samsung galaxy s6"))).click();
        System.out.println("🔍 Clicked on Samsung Galaxy S6 product.");

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='hrefch' and contains(text(),'Samsung galaxy s6')]")
)).click();
        System.out.println("🛒 Clicked 'Add to cart'.");

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String actualAlertMessage = alert.getText();
        System.out.println("🔔 Alert Message: " + actualAlertMessage);

        Assert.assertTrue(actualAlertMessage.contains("Product added"), "❌ Alert message doesn't contain 'Product added'");
        alert.accept();
        System.out.println("✅ Product added to cart successfully.");
    }

    @Test
    public void validate_placing_an_Order() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='hrefch' and contains(text(),'Samsung galaxy s6')]")
)).click();
        System.out.println("🔍 Clicked on Samsung Galaxy S6 product.");

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Add to cart']"))).click();
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
        System.out.println("🛒 Product added to cart and alert accepted.");

        wait.until(ExpectedConditions.elementToBeClickable(By.id("cartur"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Place Order']"))).click();
        System.out.println("📦 Proceeding to Place Order.");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name"))).sendKeys("sf");
        driver.findElement(By.id("country")).sendKeys("Egypt");
        driver.findElement(By.id("city")).sendKeys("Cairo");
        driver.findElement(By.id("card")).sendKeys("1234567");
        driver.findElement(By.id("month")).sendKeys("1");
        driver.findElement(By.id("year")).sendKeys("2023");
        System.out.println("📝 Filled order form.");

        driver.findElement(By.xpath("//button[text()='Purchase']")).click();

        WebElement confirmation = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(text(),'Thank you for your purchase!')]")));
        String purchaseMsg = confirmation.getText();

        Assert.assertTrue(purchaseMsg.contains("Thank you for your purchase!"), "❌ Order confirmation not found!");
        System.out.println("✅ Order placed successfully!");
    }
}
