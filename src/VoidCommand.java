public class VoidCommand extends Command {
    public VoidCommand(Player player){
        this.player = player;
    }

    @Override
    public void execute() {
        player.becomeInactive();
    }
}
