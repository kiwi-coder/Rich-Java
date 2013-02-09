import org.junit.Before;
import org.junit.Test;

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
}
