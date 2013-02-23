package tw.rich.site;

import tw.rich.Player;
import tw.rich.command.Command;
import tw.rich.tool.Tool;

public class ToolHouseSite extends Site {
    private static final String TYPE_CODE = "T";

    public ToolHouseSite() {
        super(TYPE_CODE);
    }

    public Command giveCommand(Player player) {
        // TODO: to be refactored
        Command command = super.giveCommand(player);
        if(!player.isActive())
            return command;

        if (!player.canAffordTool(Tool.makeCheapestTool())) {
            return Command.makeCommand(Command.EXIT_TOOL_HOUSE_COMMAND_TYPE, player);
        }

        String greeting = "欢迎光临道具屋， 请选择您所需要的道具：";
        String answer = prompt(greeting).toLowerCase();

        if (Command.KEY_TO_EXIT_TOOL_HOUSE.equals(answer)) {
            command = Command.makeCommand(Command.EXIT_TOOL_HOUSE_COMMAND_TYPE, player);
        } else {
            String commandPrefix = Command.BUY_TOOL_COMMAND_PREFIX;
            command = Command.makeCommand(commandPrefix + answer, player);
        }

        return command;
    }
}
