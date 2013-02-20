public class MoneyGift extends Gift {
    public static final int MONEY_GIFT_CODE = 1;
    private static final int MONEY_GIFT_AMOUNT = 2000;

    @Override
    public void open(Player player) {
        player.earnMoney(MONEY_GIFT_AMOUNT);
    }
}
