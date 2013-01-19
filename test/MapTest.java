import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MapTest {
    private static final int MAP_SIZE = 10;

    @Test
    public void test_map_size() {
        Map map = new Map(MAP_SIZE);
        assertThat(map.size(), is(MAP_SIZE));
    }
}