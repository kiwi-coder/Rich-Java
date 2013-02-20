public class GiftHouseSite extends Site {
    private static final String GIFT_HOUSE_TYPE_CODE = "G";

    public GiftHouseSite() {
        super(GIFT_HOUSE_TYPE_CODE);
    }

    public Command giveCommand(Player player) {
        super.giveCommand(player);

        String greeting = "欢迎光临礼品屋，请选择一件您 喜欢的礼品：";
        String answer = prompt(greeting);
        String commandPrefix = Command.CHOOSE_GIFT_COMMAND_PREFIX;

        Command command = Command.makeCommand(commandPrefix + answer, player);
        return command;
    }
}
