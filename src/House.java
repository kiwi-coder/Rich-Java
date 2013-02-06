public class House extends Property {
    private static final String HOUSE_TYPE_CODE = "2";
    private static final double TOLL_FEE_RATE = 2.0;

    public House() {
        super(HOUSE_TYPE_CODE);
    }

    public Site upgrade(){
        Property skyscraper = new Skyscraper();
        map.setSite(index, skyscraper);
        skyscraper.setIndex(index);
        skyscraper.setMap(map);
        skyscraper.setPlayer(player);
        skyscraper.setPrice(price);

        return skyscraper;
    }

    @Override
    public int getTollFee(){
        return (int)(price * TOLL_FEE_RATE);
    }

}
