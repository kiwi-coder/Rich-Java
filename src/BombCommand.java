public class BombCommand extends Command {
    private int distance;

    public BombCommand(Player player, int distance) {
        super();
        this.player = player;
        this.distance = distance;
    }

    @Override
    public void execute() {
        player.useTool(BombTool.BOMB_TOOL_CODE, distance);
    }
}
