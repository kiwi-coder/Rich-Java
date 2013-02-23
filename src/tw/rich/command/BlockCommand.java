package tw.rich.command;

import tw.rich.Player;
import tw.rich.tool.BlockTool;

public class BlockCommand extends Command {
    private int distance;

    public BlockCommand(Player player, int distance) {
        super();
        this.player = player;
        this.distance = distance;
    }

    @Override
    public void execute() {
        player.useTool(BlockTool.TOOL_CODE, distance);
    }
}
