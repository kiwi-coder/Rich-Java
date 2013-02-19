public abstract class Command {
    protected Player player;

    private static final String COMMAND_SEPARATOR = " ";
    private static String commandType;
    private static int commandParameter = 0;

    public static Command makeCommand(String command, Player player) {
        parseCommand(command);

        if ("roll".equals(commandType)) {
            return new RollCommand(player);
        } else if ("block".equals(commandType)) {
            return new BlockCommand(player, commandParameter);
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

    public Player getPlayer() {
        return player;
    }
}
