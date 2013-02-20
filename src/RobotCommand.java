public class RobotCommand extends Command {
    public RobotCommand(Player player) {
        super();
        this.player = player;
    }

    @Override
    public void execute() {
        player.useTool(RobotTool.ROBOT_TOOL_CODE, RobotTool.ROBOT_USAGE_DISTANCE);
    }
}
