import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SiteTest {

    private static final int DUMMY_MONEY = 0;
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
        Player player = new Player("ATuBo", site, DUMMY_MONEY);
        assertThat(site.display(), is("A"));
    }

    @Test
    public void should_not_display_if_player_is_move_out() {
        Site site = map.getSite(0);
        Player player = new Player("ATuBo", site, DUMMY_MONEY);
        player.forward(1);
        assertThat(site.display(), is("0"));
    }

    @Test
    public void should_point_mine_display_$_on_map() {
        // Given
        PointMineSite pointMineSite = new PointMineSite(80);

        // When and Then
        assertThat(pointMineSite.display(), is("$"));
    }

    @Test
    public void should_gift_house_display_G_on_map() {
        // Given
        GiftHouseSite giftHouseSite = new GiftHouseSite();

        // When and Then
        assertThat(giftHouseSite.display(), is("G"));
    }

    @Test
    public void should_magic_house_display_M_on_map() {
        // Given
        MagicHouseSite magicHouseSite = new MagicHouseSite();

        // When and Then
        assertThat(magicHouseSite.display(), is("M"));
    }

    @Test
    public void should_starting_point_display_S_on_map() {
        // Given
        StartingSite startingSite = new StartingSite();

        // When and Then
        assertThat(startingSite.display(), is("S"));
    }

    @Test
    public void should_land_display_0_on_map(){
        // Given
        Site property = new Property(new Land(200));

        // When and then
        assertThat(property.display(), is("0"));
    }

    @Test
    public void should_cabin_display_1_on_map(){
        // Given
        Site property = new Property(new Cabin(200));

        // When and then
        assertThat(property.display(), is("1"));
    }

    @Test
    public void should_house_display_2_on_map(){
        // Given
        Site site = new Property(new House(200));

        // When and then
        assertThat(site.display(), is("2"));
    }
}
