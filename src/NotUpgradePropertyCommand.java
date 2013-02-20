public class NotUpgradePropertyCommand extends Command {
    public NotUpgradePropertyCommand(Player player) {
        super();
        this.player = player;
    }

    @Override
    public void execute() {
        player.becomeInactive();
    }
}
