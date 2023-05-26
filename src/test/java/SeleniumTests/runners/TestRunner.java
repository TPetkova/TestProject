package SeleniumTests.runners;

import SeleniumTests.tests.BooksPage;
import SeleniumTests.tests.GetBookPage;
import SeleniumTests.tests.LoginPage;
import SeleniumTests.tests.UsersPage;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        LoginPage.class,
        UsersPage.class,
        BooksPage.class,
        GetBookPage.class
})
public class TestRunner {
}
