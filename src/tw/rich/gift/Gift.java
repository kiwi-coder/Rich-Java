package tw.rich.gift;

import tw.rich.Player;

public abstract class Gift {
    public abstract void open(Player player);

    public static Gift makeGift(int giftCode) {
        switch (giftCode) {
            case MoneyGift.MONEY_GIFT_CODE:
                return new MoneyGift();
            case PointGift.POINT_GIFT_CODE:
                return new PointGift();
            case GodOfLuckGift.GOD_OF_LUCK_GIFT_CODE:
                return new GodOfLuckGift();
            default:
                return new NothingGift();
        }
    }
}
