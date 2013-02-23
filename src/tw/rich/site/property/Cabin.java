package tw.rich.site.property;

public class Cabin extends PropertyLevel {
    public static final String TYPE_CODE = "1";
    private static final double TOLL_FEE_RATE = 1.0;
    private static final double SALE_PRICE_RATE = 4.0;

    public Cabin(int price) {
        super(TYPE_CODE);
        this.setPrice(price);
    }

    public PropertyLevel upgrade() {
        return new House(getPrice());
    }

    @Override
    public int getTollFee() {
        return (int) (getPrice() * TOLL_FEE_RATE);
    }

    @Override
    public int getSalePrice() {
        return (int) (getPrice() * SALE_PRICE_RATE);
    }
}
