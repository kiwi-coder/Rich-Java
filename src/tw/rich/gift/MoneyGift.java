package tw.rich.gift;

import tw.rich.Player;

public class MoneyGift extends Gift {
    public static final int GIFT_CODE = 1;
    private static final int MONEY_GIFT_AMOUNT = 2000;

    @Override
    public void open(Player player) {
        player.earnMoney(MONEY_GIFT_AMOUNT);
    }
}
