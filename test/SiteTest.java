import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SiteTest {

    private Map map;

    @Before
    public void setUp() {
        map = TestHelper.simpleMap();
    }

    @Test
    public void should_return_third_site_for_next_site_when_this_site_is_second_site() {
        Site site = map.getSite(2);
        assertThat(site.nextSite(), is(map.getSite(3)));
    }

    @Test
    public void should_return_first_site_for_next_site_when_this_site_is_last_site() {
        Site site = map.getSite(map.size() - 1);
        assertThat(site.nextSite(), is(map.getSite(0)));
    }

    @Test
    public void should_display_0_for_simple_map_site() {
        assertThat(map.getSite(0).display(), is("0"));
    }

    @Test
    public void should_display_player_if_there_is_player() {
        Site site = map.getSite(0);
        Player player = new Player(site, "ATuBo");
        assertThat(site.display(), is("A"));
    }

    @Test
    public void should_not_display_if_player_is_move_out() {
        Site site = map.getSite(0);
        Player player = new Player(site, "ATuBo");
        player.forward(1);
        assertThat(site.display(), is("0"));
    }
}
