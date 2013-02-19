public class QueryCommand extends Command {
    public QueryCommand(Player player) {
        super();
        this.player = player;
    }

    @Override
    public void execute() {
        System.out.print(player.query());
    }
}
