import sun.jvm.hotspot.memory.DictionaryEntry;

import java.util.Random;

public class RollCommand extends Command {
    private Player player;

    public RollCommand(Player player) {
        this.player = player;
    }

    @Override
    public void execute() {
        Random random = new Random();

        int steps = random.nextInt(6);
        player.forward(Dice.roll());
    }
}
