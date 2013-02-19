public class RollCommand extends Command {
    private Player player;

    public RollCommand(Player player) {
        this.player = player;
    }

    @Override
    public void execute() {
        player.forward(Dice.roll());
    }
}
