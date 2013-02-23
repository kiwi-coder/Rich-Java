import org.junit.Before;
import org.junit.Test;
import tw.rich.Color;
import tw.rich.site.property.Cabin;
import tw.rich.site.property.Land;
import tw.rich.site.property.Property;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;

public class PropertyTest {

    private Property property;

    @Before
    public void setUp() throws Exception {
        property = new Property(new Land(100));
    }

    @Test
    public void should_return_cabin_after_upgrade_land() {
        property.upgrade();
        assertThat(property.getLevel(), instanceOf(Cabin.class));
        assertThat(property.getSalePrice(), is(400));
    }

    @Test
    public void should_return_200_for_sale_price() {
        assertThat(property.getSalePrice(), is(200));
    }

    @Test
    public void should_return_50_for_toll_fee() {
        assertThat(property.getTollFee(), is(50));
    }

    @Test
    public void should_land_display_0_on_map() {
        // Given
        Property property = new Property(new Land(200));

        // When and then
        String blackZero = Color.paint(Color.ANSI_BLACK, "0");
        assertThat(property.display(), is(blackZero));
    }

    @Test
    public void should_cabin_display_1_on_map() {
        // Given
        Property property = new Property(new Cabin(200));

        // When and then
        String blackOne = Color.paint(Color.ANSI_BLACK, "1");
        assertThat(property.display(), is(blackOne));
    }
}
