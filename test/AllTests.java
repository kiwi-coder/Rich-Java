import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        GameTest.class,
        MapTest.class,
        PlayerTest.class,
        PointTest.class,
        PropertyLevelTest.class,
        PropertyTest.class,
        SiteTest.class
})

public class AllTests {
}
