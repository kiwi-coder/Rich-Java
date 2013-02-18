import java.util.*;
import java.util.Map;

public class RoundEngine {
    private RoundEngine(){}
    private static RoundEngine singleton;
    private Map<Player, Integer> roundLeftForImmovablePlayer = new HashMap<Player, Integer>();

    public static RoundEngine instance() {
        if(singleton == null){
            singleton =  new RoundEngine();
        }
        return singleton;
    }

    public void nextRound() {
        nextRoundForImmovablePlayers();
    }

    private void nextRoundForImmovablePlayers() {
        Iterator<Map.Entry<Player, Integer>> it = roundLeftForImmovablePlayer.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<Player, Integer> entry=it.next();
            Player player = entry.getKey();
            int roundLeft = roundLeftForImmovablePlayer.get(player) - 1;
            registerImmovablePlayer(player, roundLeft);
            if(roundLeft == 0){
                it.remove();
                player.setMovable();
            }
        }
    }

    public void registerImmovablePlayer(Player player, int round) {
        roundLeftForImmovablePlayer.put(player, round);
    }
}
