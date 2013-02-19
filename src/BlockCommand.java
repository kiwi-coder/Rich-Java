public class BlockCommand extends Command {
    private int distance;

    public BlockCommand(Player player, int distance) {
        super();
        this.player = player;
        this.distance = distance;
    }

    @Override
    public void execute() {
        player.useTool(BlockTool.BLOCK_TOOL_CODE, distance);
    }
}
