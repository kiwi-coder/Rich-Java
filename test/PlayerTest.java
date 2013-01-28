import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class PlayerTest {
    private final Site DUMMY_SITE = null;
    private static final int DUMMY_MONEY = 0;
    private Player player;
    private Map map;
    private final int MAP_SIZE = TestHelper.simpleMap().size();
    private final int FIRST_SITE_INDEX = 0;
    private final int LAST_SITE_INDEX = MAP_SIZE - 1;

    @Before
    public void setUp() {
        map = TestHelper.simpleMap();
        player = new Player("ATuBo", DUMMY_SITE, DUMMY_MONEY);
    }

    @Test
    public void should_return_3_after_player_move_3_steps_from_0() {
        player.setSite(map.getSite(0));
        player.forward(3);
        assertThat(player.getSite(), is(map.getSite(3)));
    }

    @Test
    public void should_player_go_back_to_starting_site_from_last_site() {
        player.setSite(map.getSite(LAST_SITE_INDEX));
        player.forward(1);
        assertThat(player.getSite(), is(map.getSite(FIRST_SITE_INDEX)));
    }

    @Test
    public void should_return_A_for_player_name_is_ATuBo() {
        assertThat(player.display(), is("A"));
    }

    @Test
    public void should_player_buy_a_land_when_it_has_no_owner_and_he_has_enough_money(){
        // Given
        Property property = new Land();
        Player player = new Player("Atubo", property, 5000);
        property.setPlayer(player);
        property.setIndex(3);
        property.setPrice(200);

        // When
        player.buyProperty();

        // Then
        // TODO: use equals methods....
        assertThat(player.getMoney(), is(4800));
        assertThat(player.getSite().getType(), is("0"));
        assertThat(((Property)player.getSite()).getOwner().getName(), is("Atubo"));
    }

    @Test
    public void should_player_not_buy_a_land_when_he_has_no_enough_money(){
        // Given
        Property property = new Land();
        Player player = new Player("Atubo", property, 180);
        property.setPlayer(player);
        property.setIndex(3);
        property.setPrice(200);

        // When
        player.buyProperty();

        // Then
        assertThat(player.getMoney(), is(180));
        assertThat(player.getSite().getType(), is("0"));
        assertTrue(((Property)player.getSite()).getOwner() == null);
    }

    @Test
    public void should_player_upgrade_his_land_to_cabin_when_he_has_enough_money(){
        // Given
        Property property = new Land();
        Player player = new Player("Atubo", property, 5000);
        property.setPlayer(player);
        property.setIndex(3);
        property.setPrice(200);
        property.setOwner(player);

        // When
        player.upgradeProperty();

        // Then
        assertThat(player.getMoney(), is(4800));
        assertThat(player.getSite().getType(), is("1"));
    }

    @Test
    public void should_not_player_upgrade_his_land_to_cabin_when_he_has_not_enough_money(){
        // Given
        Property property = new Land();
        Player player = new Player("Atubo", property, 180);
        property.setPlayer(player);
        property.setIndex(3);
        property.setPrice(200);
        property.setOwner(player);

        // When
        player.upgradeProperty();

        // Then
        assertThat(player.getMoney(), is(180));
        assertThat(player.getSite().getType(), is("0"));
    }

    @Test
    public void should_player_upgrade_its_cabin_to_house_when_he_has_enough_money(){
        // Given
        Property property = new Cabin();
        Player player = new Player("Atubo", property, 5000);
        property.setPlayer(player);
        property.setIndex(3);
        property.setPrice(200);
        property.setOwner(player);

        // When
        player.upgradeProperty();

        // Then
        assertThat(player.getMoney(), is(4800));
        assertThat(player.getSite().getType(), is("2"));
    }

    @Test
    public void should_not_player_upgrade_his_cabin_to_house_when_has_not_enough_money(){
        // Given
        Property property = new Cabin();
        Player player = new Player("Atubo", property, 180);
        property.setPlayer(player);
        property.setIndex(3);
        property.setPrice(200);
        property.setOwner(player);

        // When
        player.upgradeProperty();

        // Then
        assertThat(player.getMoney(), is(180));
        assertThat(player.getSite().getType(), is("1"));
    }

    @Test
    public void should_player_upgrade_its_house_to_skyscraper_when_he_has_enough_money(){
        // Given
        Property property = new House();
        Player player = new Player("Atubo", property, 5000);
        property.setPlayer(player);
        property.setIndex(3);
        property.setPrice(200);
        property.setOwner(player);

        // When
        player.upgradeProperty();

        // Then
        assertThat(player.getMoney(), is(4800));
        assertThat(player.getSite().getType(), is("3"));
    }

    @Test
    public void should_not_player_upgrade_his_house_to_skyscraper_when_has_not_enough_money(){
        // Given
        Property property = new House();
        Player player = new Player("Atubo", property, 180);
        property.setPlayer(player);
        property.setIndex(3);
        property.setPrice(200);
        property.setOwner(player);

        // When
        player.upgradeProperty();

        // Then
        assertThat(player.getMoney(), is(180));
        assertThat(player.getSite().getType(), is("2"));
    }

    @Test
    public void should_not_player_upgrade_its_skyscraper_when_he_has_enough_money(){
        // Given
        Property property = new Skyscraper();
        Player player = new Player("Atubo", property, 5000);
        property.setPlayer(player);
        property.setIndex(3);
        property.setPrice(200);
        property.setOwner(player);

        // When
        player.upgradeProperty();

        // Then
        assertThat(player.getMoney(), is(5000));
        assertThat(player.getSite().getType(), is("3"));
    }

    @Test
    public void should_not_player_upgrade_its_skyscraper_when_he_has_not_enough_money(){
        // Given
        Property property = new Skyscraper();
        Player player = new Player("Atubo", property, 180);
        property.setPlayer(player);
        property.setIndex(3);
        property.setPrice(200);
        property.setOwner(player);

        // When
        player.upgradeProperty();

        // Then
        assertThat(player.getMoney(), is(180));
        assertThat(player.getSite().getType(), is("3"));
    }
}