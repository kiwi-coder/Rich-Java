public class Skyscraper extends Property {
    private static final String SKYSCRAPER_TYPE_CODE = "3";
    private static final double TOLL_FEE_RATE = 4.0;

    public Skyscraper() {
        super(SKYSCRAPER_TYPE_CODE);
    }

    @Override
    public int getTollFee(){
        return (int)(price * TOLL_FEE_RATE);
    }
}
