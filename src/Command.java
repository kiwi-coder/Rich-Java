public abstract class Command {
    protected Player player;

    private static final String ROLL_COMMAND_TYPE = "roll";
    private static final String BLOCK_COMMAND_TYPE = "block";
    private static final String BOMB_COMMAND_TYPE = "bomb";
    private static final String ROBOT_COMMAND_TYPE = "robot";
    private static final String SELL_PROPERTY_COMMAND_TYPE = "sell";

    private static final String COMMAND_SEPARATOR = " ";
    private static String commandType;
    private static int commandParameter = 0;

    public static Command makeCommand(String command, Player player) {
        parseCommand(command);

        if (ROLL_COMMAND_TYPE.equals(commandType)) {
            return new RollCommand(player);
        } else if (BLOCK_COMMAND_TYPE.equals(commandType)) {
            return new BlockCommand(player, commandParameter);
        } else if (BOMB_COMMAND_TYPE.equals(commandType)) {
            return new BombCommand(player, commandParameter);
        } else if (ROBOT_COMMAND_TYPE.equals(commandType)) {
            return new RobotCommand(player);
        } else if (SELL_PROPERTY_COMMAND_TYPE.equals(commandType)) {
            return new SellPropertyCommand(player, commandParameter);
        }

        throw new InvalidCommandException();
    }

    private static void parseCommand(String command) {
        String[] strings = command.split(COMMAND_SEPARATOR);
        commandType = strings[0];

        if (strings.length == 2) {
            commandParameter = Integer.parseInt(strings[1]);
        }
    }

    public abstract void execute();

    boolean isExecutable(Player player) {
        return this.player == player;
    }

}
