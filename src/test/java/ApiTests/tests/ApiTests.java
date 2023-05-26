package ApiTests.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.json.JSONArray;
import org.json.JSONObject;
import io.restassured.http.ContentType;

public class ApiTests {

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-task.immedis.com:443/api";
        //RestAssured.baseURI = "http://qa-task.immedis.com/api";
    }

    @Test
    public void getUserById() {
        Response response = RestAssured.get("/users/1281");
        String responseBody = response.getBody().asString();
        System.out.println("Response Body: " + responseBody);
        int userId = response.jsonPath().getInt("id");
        Assert.assertEquals("User ID does not match", 1281, userId);
        String userName = response.jsonPath().getString("name");
        Assert.assertEquals("User name does not match", "Test User 99998", userName);
        int statusCode = response.getStatusCode();
        Assert.assertEquals("Status code is not 200", 200, statusCode);
    }

    @Test
    public void getAllUsers() {
        Response response = RestAssured.get("/users");
        String responseBody = response.getBody().asString();
        int statusCode = response.getStatusCode();
        Assert.assertEquals("Status code is not 200", 200, statusCode);
        JSONArray usersArray = new JSONArray(responseBody);

        for (int i = 0; i < usersArray.length(); i++) {
            JSONObject user = usersArray.getJSONObject(i);
            Assert.assertTrue(user.has("id"));
            Assert.assertTrue(user.has("name"));

            int userId = user.getInt("id");
            String userName = user.getString("name");

            Assert.assertNotNull(userId);
            Assert.assertNotNull(userName);
        }
        System.out.println("Response Body: " + responseBody);
    }

    @Test
    public void createUser() {
        String requestBody = "{ \"Name\": \"Test abc 123\" }";
        Response response = RestAssured.given().contentType(ContentType.JSON).body(requestBody).post("/users");

        int statusCode = response.getStatusCode();
        //the user is created, but the response code is 200, not 201 ? should it be 200 ?
        Assert.assertEquals("Status code is not 201", 201, statusCode);

    }

    @Test
    public void updateUserById() {
        String requestBody = "{ \"Name\": \"Test abc 123 New\" }";
        Response response = RestAssured.given().contentType(ContentType.JSON).body(requestBody).put("/users/1287");

        int statusCode = response.getStatusCode();
        Assert.assertEquals("Status code is not 200", 200, statusCode);

        String responseBody = response.getBody().asString();
        System.out.println("Response Body: " + responseBody);
        int userId = response.jsonPath().getInt("id");
        Assert.assertEquals("User ID does not match", 1287, userId);
        String userName = response.jsonPath().getString("name");
        Assert.assertEquals("User name does not match", "Test abc 123 New", userName);

    }

    @Test
    public void deleteUserById() {
        Response response = RestAssured.delete("/users/1288");

        int statusCode = response.getStatusCode();
        Assert.assertEquals("Status code is not 204", 204, statusCode);

        Response responseCheck = RestAssured.get("/users/1288");
        int statusCodeCheck = responseCheck.getStatusCode();
        Assert.assertEquals("The user is NOT deleted", 404, statusCodeCheck);
    }

    @Test
    public void createNewBook() {
        String requestBody = "{ \"name\": \"Book Of The Books 1\", \"author\": \"Some author 123\", \"genre\": \"Thriller\", \"quontity\": 22 }";
        Response response = RestAssured.given().contentType(ContentType.JSON).body(requestBody).post("/books");

        int statusCode = response.getStatusCode();
        Assert.assertEquals("Status code is not 201", 201, statusCode);

        String responseBody = response.getBody().asString();
        System.out.println("Response Body: " + responseBody);
        int userId = response.jsonPath().getInt("id");
        Assert.assertNotNull(userId);
        String userName = response.jsonPath().getString("name");
        Assert.assertEquals("User name does not match", "Book Of The Books 1", userName);
        String author = response.jsonPath().getString("author");
        Assert.assertEquals("User name does not match", "Some author 123", author);
        String genre = response.jsonPath().getString("genre");
        Assert.assertEquals("Genre name does not match", "Thriller", genre);
        int quantity = response.jsonPath().getInt("quontity");
        Assert.assertEquals("Qantity does not match", 22, quantity);
    }

    @Test
    public void getAllBooks() {
        Response response = RestAssured.get("/books");
        String responseBody = response.getBody().asString();
        int statusCode = response.getStatusCode();
        Assert.assertEquals("Status code is not 200", 200, statusCode);
        JSONArray booksArray = new JSONArray(responseBody);

        for (int i = 0; i < booksArray.length(); i++) {
            JSONObject book = booksArray.getJSONObject(i);
            Assert.assertTrue(book.has("id"));
            Assert.assertTrue(book.has("name"));
            Assert.assertTrue(book.has("author"));
            Assert.assertTrue(book.has("genre"));
            Assert.assertTrue(book.has("quontity"));

            int bookId = book.getInt("id");
            String bookName = book.getString("name");
            String bookAuthor = book.getString("author");
            String bookGenre = book.getString("genre");
            int bookQuantity = book.getInt("quontity");

            Assert.assertNotNull(bookId);
            Assert.assertNotNull(bookName);
            Assert.assertNotNull(bookAuthor);
            Assert.assertNotNull(bookGenre);
            Assert.assertNotNull(bookQuantity);
        }
        System.out.println("Response Body: " + responseBody);
    }

    @Test
    public void getBookById() {
        Response response = RestAssured.get("/books/1385");
        String responseBody = response.getBody().asString();
        System.out.println("Response Body: " + responseBody);
        int bookId = response.jsonPath().getInt("id");
        Assert.assertEquals("Book ID does not match", 1385, bookId);
        String bookName = response.jsonPath().getString("name");
        Assert.assertEquals("Book name does not match", "Test Book number 3", bookName);
        String bookauthor = response.jsonPath().getString("author");
        Assert.assertEquals("Book author does not match", "Tester Success3", bookauthor);
        String bookGenre = response.jsonPath().getString("genre");
        Assert.assertEquals("Book genre does not match", "mistery", bookGenre);
        int bookQuantity = response.jsonPath().getInt("quontity");
        Assert.assertEquals("Book quantity does not match", 50000, bookQuantity);
        int statusCode = response.getStatusCode();
        Assert.assertEquals("Status code is not 200", 200, statusCode);
    }

    @Test
    public void updateBookById() {
        String requestBody = "{ \"id\": 1387, \"name\": \"Book Of The Books 2\", \"author\": \"Tester Success3 New\", \"genre\": \"Fantasy 34\", \"quontity\": 300 }";
        Response response = RestAssured.given().contentType(ContentType.JSON).body(requestBody).put("/books/1387");

        int statusCode = response.getStatusCode();
        Assert.assertEquals("Status code is not 200", 200, statusCode);

        String responseBody = response.getBody().asString();
        System.out.println("Response Body: " + responseBody);
        int id = response.jsonPath().getInt("id");
        Assert.assertEquals("Id does not match", 1387, id);
        String bookName = response.jsonPath().getString("name");
        Assert.assertEquals("Book name does not match", "Book Of The Books 2", bookName);
        String author = response.jsonPath().getString("author");
        Assert.assertEquals("Author name does not match", "Tester Success3 New", author);
        String genre = response.jsonPath().getString("genre");
        Assert.assertEquals("Genre does not match", "Fantasy 34", genre);
        int quantity = response.jsonPath().getInt("quontity");
        Assert.assertEquals("Quantity does not match", 300, quantity);

    }

    @Test
    public void deleteBookById() {
        Response response = RestAssured.delete("/books/1399");

        int statusCode = response.getStatusCode();
        Assert.assertEquals("Status code is not 204", 204, statusCode);

        Response responseCheck = RestAssured.get("/books/1399");
        int statusCodeCheck = responseCheck.getStatusCode();
        Assert.assertEquals("The book is NOT deleted", 404, statusCodeCheck);
    }

    @Test
    public void takeABook() {
        String requestBody = "{ \"userid\": 1289, \"bookid\": 1386 }";
        Response response = RestAssured.given().contentType(ContentType.JSON).body(requestBody).post("/getbook");

        int statusCode = response.getStatusCode();
        Assert.assertEquals("Status code is not 201", 201, statusCode);

        String responseBody = response.getBody().asString();
        System.out.println("Response Body: " + responseBody);
        int userId = response.jsonPath().getInt("userid");
        Assert.assertEquals("User ID does not match", 1289, userId);
        int bookid = response.jsonPath().getInt("bookid");
        Assert.assertEquals("Book ID does not match", 1387, bookid);
        int id = response.jsonPath().getInt("id");
        Assert.assertNotNull(id);
        String date = response.jsonPath().getString("dateTaken");
        Assert.assertNotNull(date);
        String book = response.jsonPath().getString("book");
        Assert.assertNotNull(book);
        String user = response.jsonPath().getString("user");
        Assert.assertNotNull(user);

    }

    @Test
    public void getAllBooksTaken() {
        Response response = RestAssured.get("/getbook");
        String responseBody = response.getBody().asString();
        int statusCode = response.getStatusCode();
        Assert.assertEquals("Status code is not 200", 200, statusCode);
        JSONArray booksTakenArray = new JSONArray(responseBody);

        for (int i = 0; i < booksTakenArray.length(); i++) {
            JSONObject bookTaken = booksTakenArray.getJSONObject(i);
            Assert.assertTrue(bookTaken.has("id"));
            Assert.assertTrue(bookTaken.has("userId"));
            Assert.assertTrue(bookTaken.has("bookId"));
            Assert.assertTrue(bookTaken.has("dateTaken"));
            Assert.assertTrue(bookTaken.has("book"));
            Assert.assertTrue(bookTaken.has("user"));

            int id = bookTaken.getInt("id");
            int userId = bookTaken.getInt("userId");
            int bookId = bookTaken.getInt("bookId");
            String date = bookTaken.getString("dateTaken");
            String book = bookTaken.getString("book");
            String user = bookTaken.getString("user");

            Assert.assertNotNull(id);
            Assert.assertNotNull(userId);
            Assert.assertNotNull(bookId);
            Assert.assertNotNull(date);
            Assert.assertNotNull(book);
            Assert.assertNotNull(user);
        }
        System.out.println("Response Body: " + responseBody);
    }

    @Test
    public void returnBookByGetBookId() {
        Response response = RestAssured.delete("/getbook/677");

        int statusCode = response.getStatusCode();
        Assert.assertEquals("Status code is not 204", 204, statusCode);

        Response responseCheck = RestAssured.get("/getbook");
        String responseBody = responseCheck.getBody().asString();
        System.out.println("Response body:" + responseBody);
        JSONArray booksTakenArray = new JSONArray(responseBody);
        boolean isGetBookIdDeleted = false;

        for (int i = 0; i < booksTakenArray.length(); i++) {
            JSONObject bookTaken = booksTakenArray.getJSONObject(i);
            int id = bookTaken.getInt("id");
            if (id==677) {
                isGetBookIdDeleted = true;
                break;
            }
        }
        Assert.assertFalse("The GetBookId is NOT deleted",isGetBookIdDeleted);
    }
}
