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
