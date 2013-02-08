import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;

public class PropertyTest {
    private static final int DUMMY_MONEY = 0;

    @Test
    public void should_return_cabin_when_upgrade_land() {
        Land land = new Land(DUMMY_MONEY);
        land.setMap(TestHelper.simpleMap());
        assertThat(land.upgrade(), instanceOf(Cabin.class));
    }

    @Test
    public void should_return_sell_land_for_600_when_price_is_300() {
        Land land = new Land(DUMMY_MONEY);
        land.setPrice(300);
        assertThat(land.getSalePrice(), is(600));
    }

    @Test
    public void should_return_house_when_upgrade_cabin() {
        Cabin cabin = new Cabin(DUMMY_MONEY);
        cabin.setMap(TestHelper.simpleMap());
        assertThat(cabin.upgrade(), instanceOf(House.class));
    }

    @Test
    public void should_return_sell_cabin_for_1200_when_price_is_300() {
        Cabin cabin = new Cabin(DUMMY_MONEY);
        cabin.setPrice(300);
        assertThat(cabin.getSalePrice(), is(1200));
    }

    @Test
    public void should_return_skyscraper_when_upgrade_house() {
        House house = new House(DUMMY_MONEY);
        house.setMap(TestHelper.simpleMap());
        assertThat(house.upgrade(), instanceOf(Skyscraper.class));
    }

    @Test
    public void should_return_sell_house_1800_when_price_is_300() {
        House house = new House(DUMMY_MONEY);
        house.setPrice(300);
        assertThat(house.getSalePrice(), is(1800));
    }

    @Test
    public void should_return_sell_skyscraper_2400_when_price_is_300() {
        Skyscraper skyscraper = new Skyscraper(DUMMY_MONEY);
        skyscraper.setPrice(300);
        assertThat(skyscraper.getSalePrice(), is(2400));
    }
}
