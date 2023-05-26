package ApiTests.runners;
import ApiTests.tests.ApiTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ApiTests.class
})
public class TestRunner {
}
