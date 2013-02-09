import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;

public class PropertyLevelTest {
    private static final int DUMMY_MONEY = 0;

    @Test
    public void should_return_cabin_when_upgrade_land() {
        assertThat(new Land(DUMMY_MONEY).upgrade(), instanceOf(Cabin.class));
    }

    @Test
    public void should_return_sell_land_for_600_when_price_is_300() {
        assertThat(new Land(300).getSalePrice(), is(600));
    }

    @Test
    public void should_return_toll_fee_for_100_when_land_price_is_200() {
        assertThat(new Land(200).getTollFee(), is(100));
    }

    @Test
    public void should_return_0_for_display() {
        assertThat(new Land(DUMMY_MONEY).display(), is("0"));
    }

    @Test
    public void should_return_house_when_upgrade_cabin() {
        assertThat(new Cabin(DUMMY_MONEY).upgrade(), instanceOf(House.class));
    }

    @Test
    public void should_return_sell_cabin_for_1200_when_price_is_300() {
        assertThat(new Cabin(300).getSalePrice(), is(1200));
    }

    @Test
    public void should_return_toll_fee_for_200_when_cabin_price_is_200() {
        assertThat(new Cabin(200).getTollFee(), is(200));
    }

    @Test
    public void should_return_1_for_display() {
        assertThat(new Cabin(DUMMY_MONEY).display(), is("1"));
    }

    @Test
    public void should_return_skyscraper_when_upgrade_house() {
        assertThat(new House(DUMMY_MONEY).upgrade(), instanceOf(Skyscraper.class));
    }

    @Test
    public void should_return_sell_house_1800_when_price_is_300() {
        assertThat(new House(300).getSalePrice(), is(1800));
    }

    @Test
    public void should_return_toll_fee_400_when_house_price_is_200() {
        assertThat(new House(200).getTollFee(), is(400));
    }

    @Test
    public void should_return_2_for_house_display() {
        assertThat(new House(DUMMY_MONEY).display(), is("2"));
    }

    @Test
    public void should_return_sell_skyscraper_2400_when_price_is_300() {
        assertThat(new Skyscraper(300).getSalePrice(), is(2400));
    }

    @Test
    public void should_return_toll_fee_800_when_skyscraper_price_is_200() {
        assertThat(new Skyscraper(200).getTollFee(), is(800));
    }

    @Test
    public void should_return_3_for_skyscraper_display() {
        assertThat(new Skyscraper(DUMMY_MONEY).display(), is("3"));
    }
}
