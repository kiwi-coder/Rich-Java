package tw.rich.site.property;

public class House extends PropertyLevel {
    public static final String TYPE_CODE = "2";
    private static final double TOLL_FEE_RATE = 2.0;
    private static final double SALE_PRICE_RATE = 6.0;

    public House(int price) {
        super(TYPE_CODE);
        this.setPrice(price);
    }

    public PropertyLevel upgrade() {
        return new Skyscraper(getPrice());
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
