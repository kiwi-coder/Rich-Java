public class BuyToolCommand extends Command {
    private int toolCode;

    public BuyToolCommand(Player player, int toolCode) {
        super();
        this.player = player;
        this.toolCode = toolCode;
    }

    @Override
    public void execute() {
        player.buyTool(toolCode);
    }
}
