import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PropertyTest {
    @Test
    public void should_player_buy_a_property_when_it_has_no_owner_and_he_has_enough_money(){
        // Given
        Site site = new Site("0");
        Player player = new Player("Atubo", site, 5000);
        site.setPlayer(player);
        site.setIndex(3);
        site.setPrice(200);

        // When
        player.buy();

        // Then
        assertThat(player.getMoney(), is(4800));
        assertThat(site.getType(), is("1"));
    }

    @Test
    public void should_player_not_buy_a_property_when_he_has_no_enough_money(){
        // Given
        Site site = new Site("0");
        Player player = new Player("Atubo", site, 180);
        site.setPlayer(player);
        site.setIndex(3);
        site.setPrice(200);

        // When
        player.buy();

        // Then
        assertThat(player.getMoney(), is(180));
        assertThat(site.getType(), is("0"));
    }
}
