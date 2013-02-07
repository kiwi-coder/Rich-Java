public class House extends Property {
    private static final String HOUSE_TYPE_CODE = "2";
    private static final double TOLL_FEE_RATE = 2.0;
    private static final double SALE_PRICE_RATE = 6.0;

    public House() {
        super(HOUSE_TYPE_CODE);
    }

    public Site upgrade(){
        return PropertyFactory.duplicateSkyscraperFromProperty(this);
    }

    @Override
    public int getTollFee(){
        return (int)(price * TOLL_FEE_RATE);
    }

    @Override
    public int getSalePrice() {
        return (int)(price * SALE_PRICE_RATE);
    }
}
