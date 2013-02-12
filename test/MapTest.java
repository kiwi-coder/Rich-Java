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
    public void should_point_mine_display_$_on_map() {
        // Given
        PointMine pointMine = new PointMine(80);

        // When and Then
        assertThat(pointMine.display(), is("$"));
    }

    @Test
    public void should_gift_house_display_G_on_map(){
        // Given
        GiftHouse giftHouse = new GiftHouse();

        // When and Then
        assertThat(giftHouse.display(), is("G"));
    }
}