public class ToolHouseSite extends Site{
    private static final String TOOL_HOUSE_TYPE_CODE = "T";

    public ToolHouseSite() {
        super(TOOL_HOUSE_TYPE_CODE);
    }

    public Command giveCommand(Player player) {
        super.giveCommand(player);

        String greeting = "欢迎光临道具屋， 请选择您所需要的道具：";
        String answer = prompt(greeting);
        String commandPrefix = Command.BUY_TOOL_COMMAND_PREFIX;

        Command command = Command.makeCommand(commandPrefix + answer, player);
        return command;
    }
}
