package tw.rich.command;

import tw.rich.Player;

public class SellToolCommand extends Command {
    private int toolCode;

    public SellToolCommand(Player player, int toolCode) {
        super();
        this.player = player;
        this.toolCode = toolCode;
    }

    @Override
    public void execute() {
        player.sellTool(toolCode);
    }
}
