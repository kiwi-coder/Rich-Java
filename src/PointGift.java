public class PointGift extends Gift {
    public static final int POINT_GIFT_CODE = 2;
    private static final int POINT_GIFT_AMOUNT = 200;

    @Override
    void open(Player player) {
        player.earnPoints(POINT_GIFT_AMOUNT);
    }
}
