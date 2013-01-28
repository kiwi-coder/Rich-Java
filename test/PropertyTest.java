import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class PropertyTest {
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
