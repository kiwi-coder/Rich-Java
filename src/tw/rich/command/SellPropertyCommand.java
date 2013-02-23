package tw.rich.command;

import tw.rich.Player;

public class SellPropertyCommand extends Command {
    private int siteIndex;

    public SellPropertyCommand(Player player, int siteIndex) {
        super();
        this.player = player;
        this.siteIndex = siteIndex;
    }

    @Override
    public void execute() {
        player.sellPropertyByIndex(siteIndex);
    }
}
