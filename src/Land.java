public class Land extends Property {
    private static final String LAND_TYPE_CODE = "0";
    private static final double TOLL_FEE_RATE = 0.5;

    public Land() {
        super(LAND_TYPE_CODE);
    }

    public Site upgrade(){
        Property cabin = new Cabin();
        cabin.setIndex(index);
        cabin.setMap(map);
        cabin.setPlayer(player);
        cabin.setPrice(price);

        map.setSite(index, cabin);
        return cabin;
    }

    @Override
    public int getTollFee(){
        return (int)(price * TOLL_FEE_RATE);
    }
}
