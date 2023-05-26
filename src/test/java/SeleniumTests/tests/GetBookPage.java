package SeleniumTests.tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;

public class GetBookPage {
    private WebDriver driver;

    @Before
    public void setUp() {
        //System.out.println("This is start of the test");
        System.setProperty("webdriver.edge.driver", "src/main/resources/drivers/msedgedriver.exe");
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get("https://qa-task.immedis.com/GetBook");
    }

    @After
    public void tearDown() {
        //System.out.println("This is the end of the test");

        driver.quit();
    }

    @Test
    public void successfullyCreateGetBookRequest() {
        //expected quantity of the book we are going to use and create new getBook request
        driver.navigate().to("https://qa-task.immedis.com/Books");
        //int expectedQuantity = Integer.parseInt(driver.findElement(By.xpath("//tr//td[contains(text(), 'Book Of The Books')]//following-sibling::td[3]")).getText());
        driver.navigate().to("https://qa-task.immedis.com/GetBook");
        WebElement createNew = driver.findElement(By.linkText("Create New"));
        createNew.click();
        String expectedUrl = "https://qa-task.immedis.com/GetBook/Create";
        String actualUrl = driver.getCurrentUrl();
        String errorMessage = "Expected URL: " + expectedUrl + " but the Actual URL is: " + actualUrl;
        Assert.assertEquals(errorMessage,expectedUrl, actualUrl);
        WebElement user = driver.findElement(By.cssSelector("label[for='UserId']"));
        Assert.assertTrue(user.isDisplayed());
        WebElement userValue = driver.findElement(By.id("UserId"));
        Select selectUser = new Select(userValue);
        selectUser.selectByVisibleText("Test User 99998");
        WebElement book = driver.findElement(By.cssSelector("label[for='BookId']"));
        Assert.assertTrue(book.isDisplayed());
        WebElement bookValue = driver.findElement(By.id("BookId"));
        Select selectBook = new Select(bookValue);
        //here the selected value should be the name of the book, but there are authors and for the test I put author name
        selectBook.selectByVisibleText("BookAuthor0123");
        WebElement createButton = driver.findElement(By.cssSelector("input[value='Create']"));
        createButton.click();
        String expectedBooksUrl = "https://qa-task.immedis.com/GetBook";
        String actualBookUrl = driver.getCurrentUrl();
        String errorMessageBooks = "Expected URL: " + expectedBooksUrl + " but the Actual URL is: " + actualBookUrl;
        Assert.assertEquals(errorMessageBooks,expectedBooksUrl, actualBookUrl);
        driver.navigate().to("https://qa-task.immedis.com/Books");
        //int bookQuantityActual = Integer.parseInt(driver.findElement(By.xpath("//tr//td[contains(text(), 'Book Of The Books')]//following-sibling::td[3]")).getText());
        //Assert.assertEquals("Actual quantity of the book should be equal to original quantity - 1",expectedQuantity-1,bookQuantityActual);
    }

    @Test
    public void successfullyDeleteGetBookRequest() {
        //delete the get book from previous test
        //expected quantity of the book we are going to use and create new getBook request
        driver.navigate().to("https://qa-task.immedis.com/Books");
        //int expectedQuantity = Integer.parseInt(driver.findElement(By.xpath("//tr//td[contains(text(), 'Book Of The Books')]//following-sibling::td[3]")).getText());
        driver.navigate().to("https://qa-task.immedis.com/GetBook");
        WebElement delete = driver.findElement(By.xpath("//tr[td[contains(text(), 'BookAuthor0123')]]//a[contains(@href, '/GetBook/Delete/')]"));
        delete.click();
        WebElement delConfirmation = driver.findElement(By.cssSelector("input[value='Delete']"));
        delConfirmation.click();
        String expectedBooksUrl = "https://qa-task.immedis.com/GetBook";
        String actualBookUrl = driver.getCurrentUrl();
        String errorMessageBooks = "Expected URL: " + expectedBooksUrl + " but the Actual URL is: " + actualBookUrl;
        Assert.assertEquals(errorMessageBooks,expectedBooksUrl, actualBookUrl);
        WebElement bookName = driver.findElement(By.xpath("//table[contains(@class, 'table')]//td[contains(text(), 'BookAuthor0123')]"));
        Assert.assertFalse(bookName.isDisplayed());
        driver.navigate().to("https://qa-task.immedis.com/Books");
        //int bookQuantityActual = Integer.parseInt(driver.findElement(By.xpath("//tr//td[contains(text(), 'Book Of The Books')]//following-sibling::td[3]")).getText());
        //Assert.assertEquals("Actual quantity of the book should be equal to original quantity + 1",expectedQuantity+1,bookQuantityActual);
    }

    @Test
    public void successfullyEditGetBookFromRequestMainMenu() {
        WebElement edit = driver.findElement(By.xpath("//tr[td[contains(text(), 'Test Author 99999')]]//a[contains(@href, '/GetBook/Edit/')]"));
        edit.click();
        String expectedUrl = "https://qa-task.immedis.com/GetBook/Edit/677";
        String actualUrl = driver.getCurrentUrl();
        String errorMessage = "Expected URL: " + expectedUrl + " but the Actual URL is: " + actualUrl;
        Assert.assertEquals(errorMessage,expectedUrl, actualUrl);
        WebElement user = driver.findElement(By.cssSelector("label[for='UserId']"));
        Assert.assertTrue(user.isDisplayed());
        WebElement userValue = driver.findElement(By.id("UserId"));
        Select selectUser = new Select(userValue);
        selectUser.selectByVisibleText("Test 0123 4567");
        WebElement book = driver.findElement(By.cssSelector("label[for='BookId']"));
        Assert.assertTrue(book.isDisplayed());
        WebElement bookValue = driver.findElement(By.id("BookId"));
        Select selectBook = new Select(bookValue);
        selectBook.selectByVisibleText("Test Author 99999");
        WebElement saveButton = driver.findElement(By.cssSelector("input[value='Save']"));
        saveButton.click();
        String expectedBooksUrl = "https://qa-task.immedis.com/GetBook";
        String actualBookUrl = driver.getCurrentUrl();
        String errorMessageBooks = "Expected URL: " + expectedBooksUrl + " but the Actual URL is: " + actualBookUrl;
        Assert.assertEquals(errorMessageBooks,expectedBooksUrl, actualBookUrl);
        WebElement bookName = driver.findElement(By.xpath("//table[contains(@class, 'table')]//td[contains(text(), 'Test Author 99999')]"));
        Assert.assertTrue(bookName.isDisplayed());
        WebElement userName = driver.findElement(By.xpath("//table[contains(@class, 'table')]//td[contains(text(), 'Test 0123 4567')]"));
        Assert.assertTrue(userName.isDisplayed());

    }

    @Test
    public void successfullyViewDetails() {
        WebElement details = driver.findElement(By.xpath("//tr[td[contains(text(), 'Test Author 99999')]]//a[contains(@href, '/GetBook/Details/')]"));
        details.click();
        String expectedUrl = "https://qa-task.immedis.com/GetBook/Details/677";
        String actualUrl = driver.getCurrentUrl();
        String errorMessage = "Expected URL: " + expectedUrl + " but the Actual URL is: " + actualUrl;
        Assert.assertEquals(errorMessage,expectedUrl, actualUrl);
        WebElement book = driver.findElement(By.xpath("//dl[contains(@class, 'dl-horizontal')]//dt[contains(text(),'Book')]"));
        Assert.assertTrue(book.isDisplayed());
        WebElement bookValue = driver.findElement(By.xpath("//dl[contains(@class, 'dl-horizontal')]//dd[contains(text(),'Test Author 99999')]"));
        Assert.assertTrue(bookValue.isDisplayed());
        WebElement user = driver.findElement(By.xpath("//dl[contains(@class, 'dl-horizontal')]//dt[contains(text(),'User')]"));
        Assert.assertTrue(user.isDisplayed());
        WebElement userValue = driver.findElement(By.xpath("//dl[contains(@class, 'dl-horizontal')]//dd[contains(text(),'Test 0123 4567')]"));
        Assert.assertTrue(userValue.isDisplayed());

    }

    @Test
    public void successfullyEditGetBookRequestFromDetails() {
        WebElement details = driver.findElement(By.xpath("//tr[td[contains(text(), 'Test Author 99999')]]//a[contains(@href, '/GetBook/Details/')]"));
        details.click();
        String expectedUrl = "https://qa-task.immedis.com/GetBook/Details/677";
        String actualUrl = driver.getCurrentUrl();
        String errorMessage = "Expected URL: " + expectedUrl + " but the Actual URL is: " + actualUrl;
        Assert.assertEquals(errorMessage,expectedUrl, actualUrl);
        WebElement edit = driver.findElement(By.cssSelector("a[href='/GetBook/Edit/677']"));
        edit.click();
        String expectedEditUrl = "https://qa-task.immedis.com/GetBook/Edit/677";
        String actualEditUrl = driver.getCurrentUrl();
        String editErrorMessage = "Expected URL: " + expectedEditUrl + " but the Actual URL is: " + actualEditUrl;
        Assert.assertEquals(editErrorMessage,expectedEditUrl, actualEditUrl);
        WebElement user = driver.findElement(By.cssSelector("label[for='UserId']"));
        Assert.assertTrue(user.isDisplayed());
        WebElement userValue = driver.findElement(By.id("UserId"));
        Select selectUser = new Select(userValue);
        selectUser.selectByVisibleText("Test 0123 4567");
        WebElement book = driver.findElement(By.cssSelector("label[for='BookId']"));
        Assert.assertTrue(book.isDisplayed());
        WebElement bookValue = driver.findElement(By.id("BookId"));
        Select selectBook = new Select(bookValue);
        selectBook.selectByVisibleText("Test Author 99999");
        WebElement saveButton = driver.findElement(By.cssSelector("input[value='Save']"));
        saveButton.click();
        String expectedBooksUrl = "https://qa-task.immedis.com/GetBook";
        String actualBookUrl = driver.getCurrentUrl();
        String errorMessageBooks = "Expected URL: " + expectedBooksUrl + " but the Actual URL is: " + actualBookUrl;
        Assert.assertEquals(errorMessageBooks,expectedBooksUrl, actualBookUrl);
        WebElement bookName = driver.findElement(By.xpath("//table[contains(@class, 'table')]//td[contains(text(), 'Test Author 99999')]"));
        Assert.assertTrue(bookName.isDisplayed());
        WebElement userName = driver.findElement(By.xpath("//table[contains(@class, 'table')]//td[contains(text(), 'Test 0123 4567')]"));
        Assert.assertTrue(userName.isDisplayed());
    }
}
