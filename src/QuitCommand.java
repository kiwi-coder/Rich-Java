public class QuitCommand extends Command {
    public QuitCommand(Player player) {
        super();
        this.player = player;
    }

    @Override
    public void execute() {
        // TODO: this is not unit tested...
        System.exit(0);
    }
}
