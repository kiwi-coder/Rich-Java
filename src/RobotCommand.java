public class RobotCommand extends Command {
    private static final int ROBOT_USAGE_DISTANCE = 0;

    public RobotCommand(Player player) {
        super();
        this.player = player;
    }

    @Override
    public void execute() {
        player.useTool(RobotTool.ROBOT_TOOL_CODE, ROBOT_USAGE_DISTANCE);
    }
}
