public class Skyscraper extends PropertyLevel {
    private static final String SKYSCRAPER_TYPE_CODE = "3";
    private static final double TOLL_FEE_RATE = 4.0;
    private static final double SALE_PRICE_RATE = 8.0;

    public Skyscraper(int price) {
        super(SKYSCRAPER_TYPE_CODE);
        this.setPrice(price);
    }

    @Override
    public PropertyLevel upgrade() {
        return null;  //ToDo: throw exception here
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
