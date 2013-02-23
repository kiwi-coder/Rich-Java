package tw.rich.command;

import tw.rich.Player;
import tw.rich.site.property.Property;

public class BuyLandCommand extends Command {
    public BuyLandCommand(Player player) {
        super();
        this.player = player;
    }

    @Override
    public void execute() {
        player.buyProperty((Property) player.getSite());
        player.becomeInactive();
    }
}
