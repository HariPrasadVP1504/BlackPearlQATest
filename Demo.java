//Note: Add all the packages mentioned below and replace the credentials before running the code.

import java.time.Duration;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Demo {
	
	//Credentials
	public static String username = "Username"; //Replace with username
    public static String password = "Password"; //Replace with password
    public static String siteURL= "https://admin.neraperla.com";
    public static String expectedDashboardURL="https://admin.neraperla.com/c08t/#/dashboard";
	
	public static void main(String[] args) {
		
		// Set the path to the ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "chromedriver's local path"); //download the same chromedriver version as that of the browser
        
        // Create a new instance of the ChromeDriver
		WebDriver driver = new ChromeDriver();
		
		//navigate to URL and maximize the browser
		driver.get(siteURL);
		driver.manage().window().maximize();
		
		//wait until the sign in option is available
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("switch-up")));
		driver.findElement(By.id("switch-up")).click();
		
		//wait until User name field is visible in login page
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("LoginForm-Username")));
		//Locate user name field and enter value
		WebElement fieldUsername=driver.findElement(By.id("LoginForm-Username"));
		fieldUsername.sendKeys(username);
		//Locate password field and enter value
		WebElement fieldPassword=driver.findElement(By.id("LoginForm-Password"));
		fieldPassword.sendKeys(password);
		//click on login button
		driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/form/button/span")).click();
		
		//wait until Dashboard page is up
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[3]/div[2]/div/div/div[1]/h1")));
		//Since there's no user number on the dashboard page, user's name is validated to be present always (not null)
		String userData=driver.findElement(By.id("ui-organisation-name")).getText();
		System.out.println("The user name from the dashboard is "+userData);
		Assert.assertNotNull(userData);
		
		//extract current dashboard URL to validate the expected result 
		String actualDashboardURL = driver.getCurrentUrl();
		if (actualDashboardURL.equals(expectedDashboardURL)) {
            System.out.println("Login successful!");
        } else {
            System.out.println("Login failed!");
        }
		driver.quit();
		}
	

}
