package tw.rich.command;

import tw.rich.Player;

public class ExitToolHouseCommand extends Command {
    public ExitToolHouseCommand(Player player) {
        super();
        this.player = player;
    }

    @Override
    public void execute() {
        player.becomeInactive();
    }
}
