public class Cabin extends Property{
    private static final String CABIN_TYPE_CODE = "1";
    private static final double TOLL_FEE_RATE = 1.0;
    private static final double SALE_PRICE_RATE = 4.0;

    public Cabin(int price) {
        super(CABIN_TYPE_CODE);
        this.price = price;
    }

    public Site upgrade(){
        return new House(getPrice());
    }

    @Override
    public int getTollFee(){
        return (int)(price * TOLL_FEE_RATE);
    }

    @Override
    public int getSalePrice(){
        return (int)(price * SALE_PRICE_RATE);
    }
}
