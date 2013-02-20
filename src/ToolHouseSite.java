public class ToolHouseSite extends Site {
    private static final String TOOL_HOUSE_TYPE_CODE = "T";

    public ToolHouseSite() {
        super(TOOL_HOUSE_TYPE_CODE);
    }

    public Command giveCommand(Player player) {
        // TODO: to be refactored
        super.giveCommand(player);

        if (!player.canAffordTool(Tool.makeCheapestTool())) {
            return Command.makeCommand(Command.EXIT_TOOL_HOUSE_COMMAND_TYPE, player);
        }

        String greeting = "欢迎光临道具屋， 请选择您所需要的道具：";
        String answer = prompt(greeting).toLowerCase();

        Command command;
        if (Command.KEY_TO_EXIT_TOOL_HOUSE.equals(answer)) {
            command = Command.makeCommand(Command.EXIT_TOOL_HOUSE_COMMAND_TYPE, player);
        } else {
            String commandPrefix = Command.BUY_TOOL_COMMAND_PREFIX;
            command = Command.makeCommand(commandPrefix + answer, player);
        }

        return command;
    }
}
