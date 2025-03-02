import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Login {
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
    public void validate_Login_and_logout_scenario_by_valid_Data() throws InterruptedException {
        driver.findElement(By.xpath("//a[@id='login2']")).click();
        Thread.sleep(3000);

        driver.findElement(By.xpath("//input[@id='loginusername']")).sendKeys("Saif2");
        driver.findElement(By.xpath("//input[@id='loginpassword']")).sendKeys("12345");
        driver.findElement(By.xpath("//button[contains(text(),'Log in')]")).click();

        Thread.sleep(3000); // Wait for alert to appear

        String WelcomeMessage = driver.findElement(By.xpath("//a[@id='nameofuser']")).getText();
        Assert.assertTrue(WelcomeMessage.contains("Welcome Saif2"));

        driver.findElement(By.xpath("//a[@id='logout2']")).click();
        String SignUp = driver.findElement(By.xpath("//a[@id='signin2']")).getText();
        Thread.sleep(3000);
        Assert.assertTrue(SignUp.contains("Sign up"));

    }
}
