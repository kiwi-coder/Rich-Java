package tw.rich.command;

import tw.rich.Dice;
import tw.rich.Player;

public class RollCommand extends Command {
    public RollCommand(Player player) {
        super();
        this.player = player;
    }

    @Override
    public void execute() {
        player.forward(Dice.roll());
    }
}
