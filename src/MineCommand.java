public class MineCommand extends Command {
    public MineCommand(Player player) {
        super();
        this.player = player;
    }

    @Override
    public void execute() {
        player.mine((PointMineSite)player.getSite());
        player.becomeInactive();
    }
}
