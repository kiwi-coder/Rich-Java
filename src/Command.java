public abstract class Command {
    protected Player player;

    public static final String BUY_LAND_COMMAND_SUFFIX = "_buy";
    public static final String UPGRADE_PROPERTY_COMMAND_SUFFIX = "_upgrade";
    public static final String CHOOSE_GIFT_COMMAND_PREFIX = "gift ";
    public static final String BUY_TOOL_COMMAND_PREFIX = "buyTool ";

    private static final String ROLL_COMMAND_TYPE = "roll";
    private static final String BLOCK_COMMAND_TYPE = "block";
    private static final String BOMB_COMMAND_TYPE = "bomb";
    private static final String ROBOT_COMMAND_TYPE = "robot";
    private static final String SELL_PROPERTY_COMMAND_TYPE = "sell";
    private static final String SELL_TOOL_COMMAND_TYPE = "sellTool";
    private static final String QUERY_COMMAND_TYPE = "query";
    private static final String HELP_COMMAND_TYPE = "help";
    private static final String QUIT_COMMAND_TYPE = "quit";
    private static final String BUY_LAND_COMMAND_TYPE = "y_buy";
    private static final String UPGRADE_PROPERTY_COMMAND_TYPE = "y_upgrade";
    private static final String NO_BUY_LAND_COMMAND_TYPE = "n_buy";
    private static final String NOT_UPGRADE_PROPERTY_COMMAND_TYPE = "n_upgrade";
    public static final String PAY_TOLL_FEE_COMMAND_TYPE = "toll";
    private static final String CHOOSE_GIFT_COMMAND_TYPE = "gift";
    private static final String BUY_TOOL_COMMAND_TYPE = "buyTool";

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
        } else if (SELL_TOOL_COMMAND_TYPE.equals(commandType)) {
            return new SellToolCommand(player, commandParameter);
        } else if (QUERY_COMMAND_TYPE.equals(commandType)) {
            return new QueryCommand(player);
        } else if (HELP_COMMAND_TYPE.equals(commandType)) {
            return new HelpCommand(player);
        } else if (QUIT_COMMAND_TYPE.equals(commandType)) {
            return new QuitCommand(player);
        } else if (BUY_LAND_COMMAND_TYPE.equals(commandType)) {
            return new BuyLandCommand(player);
        } else if (UPGRADE_PROPERTY_COMMAND_TYPE.equals(commandType)) {
            return new UpgradePropertyCommand(player);
        } else if (NO_BUY_LAND_COMMAND_TYPE.equals(commandType)) {
            return new NotBuyLandCommand(player);
        } else if (NOT_UPGRADE_PROPERTY_COMMAND_TYPE.equals(commandType)) {
            return new NotUpgradePropertyCommand(player);
        } else if (PAY_TOLL_FEE_COMMAND_TYPE.equals(commandType)) {
            return new PayTollFeeCommand(player);
        } else if (CHOOSE_GIFT_COMMAND_TYPE.equals(commandType)) {
            return new ChooseGiftCommand(player, commandParameter);
        } else if (BUY_TOOL_COMMAND_TYPE.equals(commandType)) {
            return new BuyToolCommand(player, commandParameter);
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
