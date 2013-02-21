public class DoNothingCommand extends Command {
        public DoNothingCommand(Player player) {
            this.player = player;
        }

        @Override
        public void execute() {
            player.becomeInactive();
        }
}
