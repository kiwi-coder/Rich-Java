package tw.rich.gift;

import tw.rich.GodOfLuck;
import tw.rich.Player;

public class GodOfLuckGift extends Gift {
    public static final int GIFT_CODE = 3;

    @Override
    public void open(Player player) {
        player.setGodOfLuck(new GodOfLuck());
    }
}
