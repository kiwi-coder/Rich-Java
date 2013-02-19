/**
 * Created with IntelliJ IDEA.
 * User: goghvanmr
 * Date: 2/19/13
 * Time: 8:16 PM
 * To change this template use File | Settings | File Templates.
 */
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
