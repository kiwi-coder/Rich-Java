package tw.rich.site.property;

public class Land extends PropertyLevel {
    public static final String LAND_TYPE_CODE = "0";
    private static final double TOLL_FEE_RATE = 0.5;
    private static final double SALE_PRICE_RATE = 2.0;

    public Land(int price) {
        super(LAND_TYPE_CODE);
        this.setPrice(price);
    }

    public PropertyLevel upgrade() {
        return new Cabin(getPrice());
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
