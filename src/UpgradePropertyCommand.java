public class UpgradePropertyCommand extends Command {
    public UpgradePropertyCommand(Player player) {
        super();
        this.player = player;
    }

    @Override
    public void execute() {
        player.upgradeProperty((Property) player.getSite());
        player.becomeInactive();
    }
}
