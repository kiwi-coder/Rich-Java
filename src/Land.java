public class Land extends Property {
    private static final String LAND_TYPE_CODE = "0";
    private static final double TOLL_FEE_RATE = 0.5;
    private static final double SALE_PRICE_RATE = 2.0;

    public Land(int price) {
        super(LAND_TYPE_CODE);
        this.price = price;
    }

    public Site upgrade(){
        return new Cabin(getPrice());
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
