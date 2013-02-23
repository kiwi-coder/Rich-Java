package tw.rich.command;

import tw.rich.Player;
import tw.rich.tool.BombTool;

public class BombCommand extends Command {
    private int distance;

    public BombCommand(Player player, int distance) {
        super();
        this.player = player;
        this.distance = distance;
    }

    @Override
    public void execute() {
        player.useTool(BombTool.TOOL_CODE, distance);
    }
}
