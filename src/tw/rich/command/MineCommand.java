package tw.rich.command;

import tw.rich.Player;
import tw.rich.site.PointMineSite;

public class MineCommand extends Command {
    public MineCommand(Player player) {
        super();
        this.player = player;
    }

    @Override
    public void execute() {
        player.mine((PointMineSite) player.getSite());
        player.becomeInactive();
    }
}
