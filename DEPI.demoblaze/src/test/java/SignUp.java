import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SignUp {
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
    public void validate_SignUp_scenario_by_valid_Data() throws InterruptedException {
        driver.findElement(By.xpath("//a[@id='signin2']")).click();
        Thread.sleep(3000); // Wait for sign-up modal to appear

        // Generate a unique username to avoid duplicate registration issues
        String uniqueUsername = "Sf" + System.currentTimeMillis();
        driver.findElement(By.xpath("//input[@id='sign-username']")).sendKeys(uniqueUsername);
        driver.findElement(By.xpath("//input[@id='sign-password']")).sendKeys("Saif_127");
        driver.findElement(By.xpath("//button[contains(text(),'Sign up')]")).click();

        Thread.sleep(3000); // Wait for alert to appear

        // Switch to alert and validate the message
        Alert alert = driver.switchTo().alert();
        String actualAlertMessage = alert.getText();
        System.out.println("Alert Message: " + actualAlertMessage); // Debugging

        String expectedAlertMessage = "Sign up successful";
        Assert.assertTrue(actualAlertMessage.contains(expectedAlertMessage),
                "Alert message does not contain the expected text!");

        alert.accept();
    }
}
