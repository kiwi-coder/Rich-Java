import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MapTest {
    private static final int DUMMY_MONEY = 0;
    private Map map;

    @Before
    public void setUp() throws Exception {
        map = TestHelper.simpleMap();
    }

    @Test
    public void test_map_size() {
        assertThat(map.size(), is(8));
    }

    @Test
    public void test_map_layout() {
        String expected = "000\n"
                + "0 0\n"
                + "000\n";
        assertThat(map.display(), is(expected));
    }

    @Test
    public void test_display_ATuBo_at_starting_position() {
        String expected = "A00\n"
                + "0 0\n"
                + "000\n";
        Player player = new Player("ATuBo", map.getSite(0), DUMMY_MONEY);
        assertThat(map.display(), is(expected));
    }

    @Test
    public void test_display_property(){
        String expected = "020\n"
                + "0 0\n"
                + "000\n";

        map.setSite(1, new Property(new House(200)));
        assertThat(map.display(), is(expected));
    }
}