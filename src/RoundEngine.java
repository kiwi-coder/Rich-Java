import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class RoundEngine {
    private RoundEngine() {
    }

    private static RoundEngine singleton;
    private Map<Player, Integer> roundLeftForImmovablePlayer = new HashMap<Player, Integer>();
    private Map<Player, Integer> roundLeftForGodOfLuck = new HashMap<Player, Integer>();

    public static RoundEngine instance() {
        if (singleton == null) {
            singleton = new RoundEngine();
        }
        return singleton;
    }

    public void nextRound() {
        nextRoundForImmovablePlayers();
        nextRoundForGodsOfLuck();
    }

    private void nextRoundForGodsOfLuck() {
        Iterator<Map.Entry<Player, Integer>> it = roundLeftForGodOfLuck.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Player, Integer> entry = it.next();
            Player player = entry.getKey();
            int roundLeft = entry.getValue() - 1;
            entry.setValue(roundLeft);
            if (roundLeft == 0) {
                it.remove();
                player.setGodOfLuck(null);
            }
        }
    }

    private void nextRoundForImmovablePlayers() {
        Iterator<Map.Entry<Player, Integer>> it = roundLeftForImmovablePlayer.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Player, Integer> entry = it.next();
            Player player = entry.getKey();
            int roundLeft = entry.getValue() - 1;
            entry.setValue(roundLeft);
            if (roundLeft == 0) {
                it.remove();
                player.setMovable();
            }
        }
    }

    public void registerImmovablePlayer(Player player, int round) {
        roundLeftForImmovablePlayer.put(player, round);
    }

    public void registerPlayerWithGodOfLuck(Player player, int round) {
        roundLeftForGodOfLuck.put(player, round);
    }
}
