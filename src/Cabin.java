public class Cabin extends Property{
    private static final String CABIN_TYPE_CODE = "1";
    private static final double TOLL_FEE_RATE = 1.0;
    private static final double SALE_PRICE_RATE = 4.0;

    public Cabin() {
        super(CABIN_TYPE_CODE);
    }

    public Site upgrade(){
        Property house = new House();
        house.setIndex(index);
        house.setMap(map);
        house.setPlayer(player);
        house.setPrice(price);

        map.setSite(index, house);
        return house;
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
