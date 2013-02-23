package tw.rich.command;

import tw.rich.Player;
import tw.rich.site.property.Property;

public class PayTollFeeCommand extends Command {
    public PayTollFeeCommand(Player player) {
        super();
        this.player = player;
    }

    @Override
    public void execute() {
        if (!player.hasGodOfLuck())
            player.payTollFee((Property) player.getSite());
        player.becomeInactive();
    }
}
