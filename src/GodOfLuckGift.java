public class GodOfLuckGift extends Gift {
    public static final int GOD_OF_LUCK_GIFT_CODE = 3;

    @Override
    void open(Player player) {
        player.setGodOfLuck(new GodOfLuck());
    }
}
