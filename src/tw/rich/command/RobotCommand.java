package tw.rich.command;

import tw.rich.Player;
import tw.rich.tool.RobotTool;

public class RobotCommand extends Command {
    public RobotCommand(Player player) {
        super();
        this.player = player;
    }

    @Override
    public void execute() {
        player.useTool(RobotTool.TOOL_CODE, RobotTool.USAGE_DISTANCE);
    }
}
