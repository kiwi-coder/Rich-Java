package tw.rich.command;

import tw.rich.Player;

public class NotBuyLandCommand extends Command {
    public NotBuyLandCommand(Player player) {
        super();
        this.player = player;
    }

    @Override
    public void execute() {
        player.becomeInactive();
    }
}
