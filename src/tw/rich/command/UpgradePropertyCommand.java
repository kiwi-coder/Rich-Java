package tw.rich.command;

import tw.rich.Player;
import tw.rich.site.property.Property;

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
