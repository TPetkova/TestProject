package SeleniumTests.tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class BooksPage {
    private WebDriver driver;

    @Before
    public void setUp() {
        //System.out.println("This is start of the test");
        System.setProperty("webdriver.edge.driver", "src/main/resources/drivers/msedgedriver.exe");
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get("https://qa-task.immedis.com/Books");
    }

    @After
    public void tearDown() {
        //System.out.println("This is the end of the test");

        driver.quit();
    }

    @Test
    public void successfullyCreateBook() {
        WebElement createNew = driver.findElement(By.linkText("Create New"));
        createNew.click();
        String expectedUrl = "https://qa-task.immedis.com/Books/Create";
        String actualUrl = driver.getCurrentUrl();
        String errorMessage = "Expected URL: " + expectedUrl + " but the Actual URL is: " + actualUrl;
        Assert.assertEquals(errorMessage,expectedUrl, actualUrl);
        WebElement name = driver.findElement(By.cssSelector("label[for='Name']"));
        Assert.assertTrue(name.isDisplayed());
        WebElement nameValue = driver.findElement(By.id("Name"));
        nameValue.sendKeys("Test Book 99999");
        WebElement author = driver.findElement(By.cssSelector("label[for='Author']"));
        Assert.assertTrue(author.isDisplayed());
        WebElement authorValue = driver.findElement(By.id("Author"));
        authorValue.sendKeys("Test Author 99999");
        WebElement genre = driver.findElement(By.cssSelector("label[for='Genre']"));
        Assert.assertTrue(genre.isDisplayed());
        WebElement genreValue = driver.findElement(By.id("Genre"));
        genreValue.sendKeys("Mistery 99999");
        WebElement quantity = driver.findElement(By.cssSelector("label[for='Quontity']"));
        Assert.assertTrue(quantity.isDisplayed());
        WebElement quantityValue = driver.findElement(By.id("Quontity"));
        quantityValue.sendKeys("99");
        WebElement createButton = driver.findElement(By.cssSelector("input[value='Create']"));
        createButton.click();
        String expectedBooksUrl = "https://qa-task.immedis.com/Books";
        String actualBookUrl = driver.getCurrentUrl();
        String errorMessageBooks = "Expected URL: " + expectedBooksUrl + " but the Actual URL is: " + actualBookUrl;
        Assert.assertEquals(errorMessageBooks,expectedBooksUrl, actualBookUrl);
        WebElement bookName = driver.findElement(By.xpath("//table[contains(@class, 'table')]//td[contains(text(), 'Test Book 99999')]"));
        Assert.assertTrue(bookName.isDisplayed());
        WebElement bookAuthor = driver.findElement(By.xpath("//table[contains(@class, 'table')]//td[contains(text(), 'Test Author 99999')]"));
        Assert.assertTrue(bookAuthor.isDisplayed());
        WebElement bookGenre = driver.findElement(By.xpath("//table[contains(@class, 'table')]//td[contains(text(), 'Mistery 99999')]"));
        Assert.assertTrue(bookGenre.isDisplayed());
        WebElement bookQuantity = driver.findElement(By.xpath("//table[contains(@class, 'table')]//td[contains(text(), '99')]"));
        Assert.assertTrue(bookQuantity.isDisplayed());
    }

    @Test
    public void successfullyDeleteBook() {
        //delete the book from previous test
        WebElement delete = driver.findElement(By.xpath("//tr[td[contains(text(), 'Test Book 99999')]]//a[contains(@href, '/Books/Delete/')]"));
        delete.click();
//        String expectedUrl = "https://qa-task.immedis.com/Books/Delete/";
//        String actualUrl = driver.getCurrentUrl();
//        String errorMessage = "Expected URL: " + expectedUrl + " but the Actual URL is: " + actualUrl;
//        Assert.assertEquals(errorMessage,expectedUrl, actualUrl);
        WebElement delConfirmation = driver.findElement(By.cssSelector("input[value='Delete']"));
        delConfirmation.click();
        String expectedBooksUrl = "https://qa-task.immedis.com/Books";
        String actualBookUrl = driver.getCurrentUrl();
        String errorMessageBooks = "Expected URL: " + expectedBooksUrl + " but the Actual URL is: " + actualBookUrl;
        Assert.assertEquals(errorMessageBooks,expectedBooksUrl, actualBookUrl);
        WebElement bookName = driver.findElement(By.xpath("//table[contains(@class, 'table')]//td[contains(text(), 'Test Book 99999')]"));
        Assert.assertFalse(bookName.isDisplayed());
    }

    @Test
    public void successfullyEditBookFromMainMenu() {
        WebElement edit = driver.findElement(By.xpath("//tr[td[contains(text(), 'BookBook0123')]]//a[contains(@href, '/Books/Edit/')]"));
        edit.click();
        String expectedUrl = "https://qa-task.immedis.com/Books/Edit/1386";
        String actualUrl = driver.getCurrentUrl();
        String errorMessage = "Expected URL: " + expectedUrl + " but the Actual URL is: " + actualUrl;
        Assert.assertEquals(errorMessage,expectedUrl, actualUrl);
        WebElement name = driver.findElement(By.cssSelector("label[for='Name']"));
        Assert.assertTrue(name.isDisplayed());
        WebElement nameValue = driver.findElement(By.id("Name"));
        nameValue.clear();
        nameValue.sendKeys("BookBook0123");
        WebElement author = driver.findElement(By.cssSelector("label[for='Author']"));
        Assert.assertTrue(author.isDisplayed());
        WebElement authorValue = driver.findElement(By.id("Author"));
        authorValue.clear();
        authorValue.sendKeys("BookAuthor0123");
        WebElement genre = driver.findElement(By.cssSelector("label[for='Genre']"));
        Assert.assertTrue(genre.isDisplayed());
        WebElement genreValue = driver.findElement(By.id("Genre"));
        genreValue.clear();
        genreValue.sendKeys("hello");
        WebElement quantity = driver.findElement(By.cssSelector("label[for='Quontity']"));
        Assert.assertTrue(quantity.isDisplayed());
        WebElement quantityValue = driver.findElement(By.id("Quontity"));
        quantityValue.clear();
        quantityValue.sendKeys("10");
        WebElement saveButton = driver.findElement(By.cssSelector("input[value='Save']"));
        saveButton.click();
        String expectedBooksUrl = "https://qa-task.immedis.com/Books";
        String actualBookUrl = driver.getCurrentUrl();
        String errorMessageBooks = "Expected URL: " + expectedBooksUrl + " but the Actual URL is: " + actualBookUrl;
        Assert.assertEquals(errorMessageBooks,expectedBooksUrl, actualBookUrl);
        WebElement bookName = driver.findElement(By.xpath("//table[contains(@class, 'table')]//td[contains(text(), 'BookBook0123')]"));
        Assert.assertTrue(bookName.isDisplayed());
        WebElement bookAuthor = driver.findElement(By.xpath("//table[contains(@class, 'table')]//td[contains(text(), 'BookAuthor0123')]"));
        Assert.assertTrue(bookAuthor.isDisplayed());
        WebElement bookGenre = driver.findElement(By.xpath("//table[contains(@class, 'table')]//td[contains(text(), 'hello')]"));
        Assert.assertTrue(bookGenre.isDisplayed());
        WebElement bookQuantity = driver.findElement(By.xpath("//table[contains(@class, 'table')]//td[contains(text(), '10')]"));
        Assert.assertTrue(bookQuantity.isDisplayed());

    }

    @Test
    public void successfullyViewDetails() {
        WebElement details = driver.findElement(By.xpath("//tr[td[contains(text(), 'BookBook0123')]]//a[contains(@href, '/Books/Details/')]"));
        details.click();
        String expectedUrl = "https://qa-task.immedis.com/Books/Details/1386";
        String actualUrl = driver.getCurrentUrl();
        String errorMessage = "Expected URL: " + expectedUrl + " but the Actual URL is: " + actualUrl;
        Assert.assertEquals(errorMessage,expectedUrl, actualUrl);
        WebElement name = driver.findElement(By.xpath("//dl[contains(@class, 'dl-horizontal')]//dt[contains(text(),'Name')]"));
        Assert.assertTrue(name.isDisplayed());
        WebElement nameValue = driver.findElement(By.xpath("//dl[contains(@class, 'dl-horizontal')]//dd[contains(text(),'BookBook0123')]"));
        Assert.assertTrue(nameValue.isDisplayed());
        WebElement author = driver.findElement(By.xpath("//dl[contains(@class, 'dl-horizontal')]//dt[contains(text(),'Author')]"));
        Assert.assertTrue(author.isDisplayed());
        WebElement authorValue = driver.findElement(By.xpath("//dl[contains(@class, 'dl-horizontal')]//dd[contains(text(),'BookAuthor0123')]"));
        Assert.assertTrue(authorValue.isDisplayed());
        WebElement genre = driver.findElement(By.xpath("//dl[contains(@class, 'dl-horizontal')]//dt[contains(text(),'Genre')]"));
        Assert.assertTrue(genre.isDisplayed());
        WebElement genreValue = driver.findElement(By.xpath("//dl[contains(@class, 'dl-horizontal')]//dd[contains(text(),'hello')]"));
        Assert.assertTrue(genreValue.isDisplayed());
        WebElement quontity = driver.findElement(By.xpath("//dl[contains(@class, 'dl-horizontal')]//dt[contains(text(),'Quontity')]"));
        Assert.assertTrue(quontity.isDisplayed());
        WebElement quontityValue = driver.findElement(By.xpath("//dl[contains(@class, 'dl-horizontal')]//dd[contains(text(),'10')]"));
        Assert.assertTrue(quontityValue.isDisplayed());

    }

    @Test
    public void successfullyEditBookFromDetails() {
        WebElement details = driver.findElement(By.xpath("//tr[td[contains(text(), 'BookBook0123')]]//a[contains(@href, '/Books/Details/')]"));
        details.click();
        String expectedUrl = "https://qa-task.immedis.com/Books/Details/1386";
        String actualUrl = driver.getCurrentUrl();
        String errorMessage = "Expected URL: " + expectedUrl + " but the Actual URL is: " + actualUrl;
        Assert.assertEquals(errorMessage,expectedUrl, actualUrl);
        WebElement edit = driver.findElement(By.cssSelector("a[href='/Books/Edit/1386']"));
        edit.click();
        String expectedEditUrl = "https://qa-task.immedis.com/Books/Edit/1386";
        String actualEditUrl = driver.getCurrentUrl();
        String editErrorMessage = "Expected URL: " + expectedEditUrl + " but the Actual URL is: " + actualEditUrl;
        Assert.assertEquals(editErrorMessage,expectedEditUrl, actualEditUrl);
        WebElement name = driver.findElement(By.cssSelector("label[for='Name']"));
        Assert.assertTrue(name.isDisplayed());
        WebElement nameValue = driver.findElement(By.id("Name"));
        nameValue.clear();
        nameValue.sendKeys("BookBook0123");
        WebElement author = driver.findElement((By.cssSelector("label[for='Author']")));
        Assert.assertTrue(author.isDisplayed());
        WebElement authorValue = driver.findElement(By.id("Author"));
        authorValue.clear();
        authorValue.sendKeys("BookAuthor0123");
        WebElement genre = driver.findElement(By.cssSelector("label[for='Genre']"));
        Assert.assertTrue(genre.isDisplayed());
        WebElement genreValue = driver.findElement(By.id("Genre"));
        genreValue.clear();
        genreValue.sendKeys("hello");
        WebElement quontity = driver.findElement(By.cssSelector("label[for='Quontity']"));
        Assert.assertTrue(quontity.isDisplayed());
        WebElement quontityValue = driver.findElement(By.id("Quontity"));
        quontityValue.clear();
        quontityValue.sendKeys("10");
        WebElement save = driver.findElement(By.cssSelector("input[class*='btn']"));
        save.click();
        String expectedBookUrl = "https://qa-task.immedis.com/Books";
        String actualBookUrl = driver.getCurrentUrl();
        String bookErrorMessage = "Expected URL: " + expectedBookUrl + " but the Actual URL is: " + actualBookUrl;
        Assert.assertEquals(bookErrorMessage,expectedBookUrl, actualBookUrl);
        WebElement book = driver.findElement(By.xpath("//table[contains(@class, 'table')]//td[contains(text(), 'Test Book 99999')]"));
        Assert.assertTrue(book.isDisplayed());
    }

}
