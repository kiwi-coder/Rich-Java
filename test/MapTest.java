import org.junit.Before;
import org.junit.Test;
import tw.rich.Color;
import tw.rich.Map;
import tw.rich.Player;
import tw.rich.site.property.House;
import tw.rich.site.property.Property;

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
        String blackZero = Color.paint(Color.ANSI_BLACK, "0");
        String expected = blackZero + blackZero + blackZero + "\n" +
                blackZero + " " + blackZero + "\n" +
                blackZero + blackZero + blackZero + "\n";
        assertThat(map.display(), is(expected));
    }

    @Test
    public void test_display_ATuBo_at_starting_position() {
        String blackZero = Color.paint(Color.ANSI_BLACK, "0");
        String blackA = Color.paint(Color.ANSI_BLACK, "A");
        String expected = blackA + blackZero + blackZero + "\n" +
                blackZero + " " + blackZero + "\n" +
                blackZero + blackZero + blackZero + "\n";
        Player player = new Player("ATuBo", map.getSite(0), DUMMY_MONEY);
        assertThat(map.display(), is(expected));
    }

    @Test
    public void test_display_property() {
        String blackZero = Color.paint(Color.ANSI_BLACK, "0");
        String blackTwo = Color.paint(Color.ANSI_BLACK, "2");
        String expected = blackZero + blackTwo + blackZero + "\n" +
                blackZero + " " + blackZero + "\n" +
                blackZero + blackZero + blackZero + "\n";

        map.setSite(1, new Property(new House(200)));
        assertThat(map.display(), is(expected));
    }
}