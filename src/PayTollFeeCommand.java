public class PayTollFeeCommand extends Command {
    public PayTollFeeCommand(Player player) {
        super();
        this.player = player;
    }

    @Override
    public void execute() {
        player.payTollFee((Property)player.getSite());
    }
}
