package SeleniumTests.tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

public class LoginPage {
    private WebDriver driver;

    @Before
    public void setUp() {
        //System.out.println("This is start of the test");
        System.setProperty("webdriver.edge.driver", "src/main/resources/drivers/msedgedriver.exe");
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get("https://qa-task.immedis.com/");
    }

    @After
    public void tearDown() {
        //System.out.println("This is the end of the test");

        driver.quit();
    }

    @Test
    public void successfullyLogin() {
        WebElement userName = driver.findElement(By.name("username"));
        userName.clear();
        userName.sendKeys("admin");
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("123456");
        WebElement signIn = driver.findElement(By.xpath("//div[text()='Sign In']"));
        signIn.click();
        String expectedUrl = "https://qa-task.immedis.com/Users";
        String actualUrl = driver.getCurrentUrl();
        String errorMessage = "Expected URL: " + expectedUrl + " but the Actual URL is: " + actualUrl;
        Assert.assertEquals(errorMessage,expectedUrl, actualUrl);

    }

    @Test
    public void successfullyChangeForgottenPassword() {
        WebElement forgottenPassword = driver.findElement(By.linkText("Here!"));
        forgottenPassword.click();
        String expectedUrl = "https://qa-task.immedis.com/Forgot";
        String actualUrl = driver.getCurrentUrl();
        String errorMessage = "Expected URL: " + expectedUrl + " but the Actual URL is: " + actualUrl;
        Assert.assertEquals(errorMessage,expectedUrl, actualUrl);
        //find some element on the page
    }

    @Test
    public void successfullySignUpForRegistration() {
        WebElement signUp = driver.findElement(By.xpath("//div[text()='Sign Up']"));
        signUp.click();
        String expectedUrl = "https://qa-task.immedis.com/Registration";
        String actualUrl = driver.getCurrentUrl();
        String errorMessage = "Expected URL: " + expectedUrl + " but the Actual URL is: " + actualUrl;
        Assert.assertEquals(errorMessage,expectedUrl, actualUrl);
    }

    @Test
    public void loginWithIncorrectCredentials() {
        WebElement userName = driver.findElement(By.name("username"));
        userName.clear();
        userName.sendKeys("username");
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("password");
        WebElement signIn = driver.findElement(By.xpath("//div[text()='Sign In']"));
        signIn.click();
        String expectedUrl = "https://qa-task.immedis.com/";
        String actualUrl = driver.getCurrentUrl();
        String errorMessage = "Expected URL: " + expectedUrl + " but the Actual URL is: " + actualUrl;
        Assert.assertEquals(errorMessage,expectedUrl, actualUrl);
        //assert some error message is displayed
    }

}
