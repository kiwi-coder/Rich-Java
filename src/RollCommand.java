public class RollCommand extends Command {
    public RollCommand(Player player) {
        this.player = player;
    }

    @Override
    public void execute() {
        player.forward(Dice.roll());
    }
}
