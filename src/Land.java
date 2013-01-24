public class Land extends Property {
    static final String LAND_TYPE_CODE = "0";
    public Land() {
        super(LAND_TYPE_CODE);
    }

    public Site upgrade(){
        Property cabin = new Cabin();
        cabin.setIndex(index);
        cabin.setMap(map);
        cabin.setPlayer(player);
        cabin.setPrice(price);

        return cabin;
    }
}
