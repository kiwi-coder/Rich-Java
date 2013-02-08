import org.junit.Test;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;

public class PropertyTest {
    @Test
    public void should_return_cabin_when_upgrade_land() {
        Land land = new Land();
        land.setMap(TestHelper.simpleMap());
        assertThat(land.upgrade(), instanceOf(Cabin.class));
    }

    @Test
    public void should_return_house_when_upgrade_cabin() {
        Cabin cabin = new Cabin();
        cabin.setMap(TestHelper.simpleMap());
        assertThat(cabin.upgrade(), instanceOf(House.class));
    }

    @Test
    public void should_return_skyscraper_when_upgrade_house() {
        House house = new House();
        house.setMap(TestHelper.simpleMap());
        assertThat(house.upgrade(), instanceOf(Skyscraper.class));
    }
}
