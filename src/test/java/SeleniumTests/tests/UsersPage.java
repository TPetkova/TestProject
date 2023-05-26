package SeleniumTests.tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

public class UsersPage {
    private WebDriver driver;

    @Before
    public void setUp() {
        //System.out.println("This is start of the test");
        System.setProperty("webdriver.edge.driver", "src/main/resources/drivers/msedgedriver.exe");
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get("https://qa-task.immedis.com/Users");
    }

    @After
    public void tearDown() {
        //System.out.println("This is the end of the test");

        driver.quit();
    }

    @Test
    public void successfullyCreateNewUser() {
        WebElement createNew = driver.findElement(By.linkText("Create New"));
        createNew.click();
        String expectedUrl = "https://qa-task.immedis.com/Users/Create";
        String actualUrl = driver.getCurrentUrl();
        String errorMessage = "Expected URL: " + expectedUrl + " but the Actual URL is: " + actualUrl;
        Assert.assertEquals(errorMessage,expectedUrl, actualUrl);
        WebElement name = driver.findElement(By.id("Name"));
        name.sendKeys("Test User 99999");
        WebElement createButton = driver.findElement(By.cssSelector("input[value='Create']"));
        createButton.click();
        String expectedUsersUrl = "https://qa-task.immedis.com/Users";
        String actualUsersUrl = driver.getCurrentUrl();
        String errorMessageUsers = "Expected URL: " + expectedUsersUrl + " but the Actual URL is: " + actualUsersUrl;
        Assert.assertEquals(errorMessageUsers,expectedUsersUrl, actualUsersUrl);
        WebElement user = driver.findElement(By.xpath("//table[contains(@class, 'table')]//td[contains(text(), 'Test User 99999')]"));
        Assert.assertTrue(user.isDisplayed());
    }

    @Test
    public void successfullyDeleteUser() {
        //delete the user from previous test
        WebElement delete = driver.findElement(By.xpath("//table[contains(@class, 'table')]//td//a[@href='/api/Users/1282']"));
        delete.click();
        String expectedUrl = "https://qa-task.immedis.com/Users/Delete/1282";
        String actualUrl = driver.getCurrentUrl();
        String errorMessage = "Expected URL: " + expectedUrl + " but the Actual URL is: " + actualUrl;
        Assert.assertEquals(errorMessage,expectedUrl, actualUrl);
        //find some confirmation for deletion

    }

    @Test
    public void successfullyEditUserFromMainMenu() {
        WebElement edit = driver.findElement(By.xpath("//table[contains(@class, 'table')]//td//a[@href='/Home/Edit/1282']"));
        edit.click();
        String expectedUrl = "https://qa-task.immedis.com/Users/Edit/1282";
        String actualUrl = driver.getCurrentUrl();
        String errorMessage = "Expected URL: " + expectedUrl + " but the Actual URL is: " + actualUrl;
        Assert.assertEquals(errorMessage,expectedUrl, actualUrl);
        //find fields for editing

    }

    @Test
    public void successfullyViewDetails() {
        WebElement details = driver.findElement(By.xpath("//table[contains(@class, 'table')]//td//a[@href='/Users/Details/1282']"));
        details.click();
        String expectedUrl = "https://qa-task.immedis.com/Users/Details/1282";
        String actualUrl = driver.getCurrentUrl();
        String errorMessage = "Expected URL: " + expectedUrl + " but the Actual URL is: " + actualUrl;
        Assert.assertEquals(errorMessage,expectedUrl, actualUrl);
        WebElement name = driver.findElement(By.xpath("//dl[contains(@class, 'dl-horizontal')]//dt[contains(text(),'Name')]"));
        Assert.assertTrue(name.isDisplayed());
        WebElement nameValue = driver.findElement(By.xpath("//dl[contains(@class, 'dl-horizontal')]//dd[contains(text(),'Test User 99999')]"));
        Assert.assertTrue(nameValue.isDisplayed());

    }

    @Test
    public void successfullyEditUserFromDetails() {
        WebElement details = driver.findElement(By.xpath("//table[contains(@class, 'table')]//td//a[@href='/Users/Details/1281']"));
        details.click();
        String expectedUrl = "https://qa-task.immedis.com/Users/Details/1281";
        String actualUrl = driver.getCurrentUrl();
        String errorMessage = "Expected URL: " + expectedUrl + " but the Actual URL is: " + actualUrl;
        Assert.assertEquals(errorMessage,expectedUrl, actualUrl);
        WebElement edit = driver.findElement(By.cssSelector("a[href='/Users/Edit/1281']"));
        edit.click();
        String expectedEditUrl = "https://qa-task.immedis.com/Users/Edit/1281";
        String actualEditUrl = driver.getCurrentUrl();
        String editErrorMessage = "Expected URL: " + expectedEditUrl + " but the Actual URL is: " + actualEditUrl;
        Assert.assertEquals(editErrorMessage,expectedEditUrl, actualEditUrl);
        WebElement name = driver.findElement(By.cssSelector("label[for='Name']"));
        Assert.assertTrue(name.isDisplayed());
        WebElement nameValue = driver.findElement(By.cssSelector("input[name='Name']"));
        nameValue.clear();
        nameValue.sendKeys("Test User 99998");
        WebElement save = driver.findElement(By.cssSelector("input[class*='btn']"));
        save.click();
        String expectedUserUrl = "https://qa-task.immedis.com/Users";
        String actualUserUrl = driver.getCurrentUrl();
        String userErrorMessage = "Expected URL: " + expectedUserUrl + " but the Actual URL is: " + actualUserUrl;
        Assert.assertEquals(userErrorMessage,expectedUserUrl, actualUserUrl);
        WebElement user = driver.findElement(By.xpath("//table[contains(@class, 'table')]//td[contains(text(), 'Test User 99998')]"));
        Assert.assertTrue(user.isDisplayed());
    }

}
