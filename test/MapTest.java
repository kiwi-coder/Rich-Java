import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class MapTest {

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
        Player player = new Player(map, "ATuBo");
        Site site = new Site("0", player, 0);
        player.setSite(site);
        map.setSite(0, site);
        assertEquals(expected, map.display());
    }
}