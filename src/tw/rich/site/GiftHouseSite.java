package tw.rich.site;

import tw.rich.Player;
import tw.rich.command.Command;

public class GiftHouseSite extends Site {
    private static final String TYPE_CODE = "G";

    public GiftHouseSite() {
        super(TYPE_CODE);
    }

    public Command giveCommand(Player player) {
        Command command = super.giveCommand(player);
        if(!player.isActive()) return command;

        String greeting = "欢迎光临礼品屋，请选择一件您 喜欢的礼品：";
        String answer = prompt(greeting);
        String commandPrefix = Command.CHOOSE_GIFT_COMMAND_PREFIX;

        return Command.makeCommand(commandPrefix + answer, player);
    }
}
