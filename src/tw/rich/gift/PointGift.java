package tw.rich.gift;

import tw.rich.Player;

public class PointGift extends Gift {
    public static final int GIFT_CODE = 2;
    private static final int POINT_GIFT_AMOUNT = 200;

    @Override
    public void open(Player player) {
        player.earnPoints(POINT_GIFT_AMOUNT);
    }
}
