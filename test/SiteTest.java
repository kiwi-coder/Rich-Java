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
        String blackZero = Color.paint(Color.ANSI_BLACK, "0");
        assertThat(map.getSite(0).display(), is(blackZero));
    }

    @Test
    public void should_display_player_if_there_is_player() {
        Site site = map.getSite(0);
        Player player = new Player("ATuBo", site, DUMMY_MONEY);
        String blackA = Color.paint(Color.ANSI_BLACK, "A");
        assertThat(site.display(), is(blackA));
    }

    @Test
    public void should_not_display_if_player_is_move_out() {
        Site site = map.getSite(0);
        Player player = new Player("ATuBo", site, DUMMY_MONEY);
        player.forward(1);
        String blackZero = Color.paint(Color.ANSI_BLACK, "0");
        assertThat(site.display(), is(blackZero));
    }

    @Test
    public void should_point_mine_display_$_on_map() {
        // Given
        PointMineSite pointMineSite = new PointMineSite(80);

        // When and Then
        String blackDollar = Color.paint(Color.ANSI_BLACK, "$");
        assertThat(pointMineSite.display(), is(blackDollar));
    }

    @Test
    public void should_gift_house_display_G_on_map() {
        // Given
        GiftHouseSite giftHouseSite = new GiftHouseSite();

        // When and Then
        String blackG = Color.paint(Color.ANSI_BLACK, "G");
        assertThat(giftHouseSite.display(), is(blackG));
    }

    @Test
    public void should_magic_house_display_M_on_map() {
        // Given
        MagicHouseSite magicHouseSite = new MagicHouseSite();

        // When and Then
        String blackM = Color.paint(Color.ANSI_BLACK, "M");
        assertThat(magicHouseSite.display(), is(blackM));
    }

    @Test
    public void should_starting_point_display_S_on_map() {
        // Given
        StartingSite startingSite = new StartingSite();

        // When and Then
        String blackS = Color.paint(Color.ANSI_BLACK, "S");
        assertThat(startingSite.display(), is(blackS));
    }

    @Test
    public void should_land_display_0_on_map(){
        // Given
        Site property = new Property(new Land(200));

        // When and then
        String blackZero = Color.paint(Color.ANSI_BLACK, "0");
        assertThat(property.display(), is(blackZero));
    }

    @Test
    public void should_cabin_display_1_on_map(){
        // Given
        Site property = new Property(new Cabin(200));

        // When and then
        String blackOne = Color.paint(Color.ANSI_BLACK, "1");
        assertThat(property.display(), is(blackOne));
    }

    @Test
    public void should_house_display_2_on_map(){
        // Given
        Site site = new Property(new House(200));

        // When and then
        String blackTwo = Color.paint(Color.ANSI_BLACK, "2");
        assertThat(site.display(), is(blackTwo));
    }

    @Test
    public void should_house_display_red_2_on_map_if_owner_is_red_colored(){
        // Given
        Property property = new Property(new House(200));
        Player player = new Player("Atubo", Color.ANSI_RED, null, 5000);
        property.setOwner(player);

        // When and then
        String redTwo = Color.paint(Color.ANSI_RED, "2");
        assertThat(property.display(), is(redTwo));
    }
}
