package tw.rich.site;

import tw.rich.Player;
import tw.rich.command.Command;
import tw.rich.command.MineCommand;

public class PointMineSite extends Site {
    private static final String POINT_MINE_TYPE_CODE = "$";
    private int point;

    public PointMineSite(int point) {
        super(POINT_MINE_TYPE_CODE);
        this.point = point;
    }

    public int getPoint() {
        return point;
    }

    public Command giveCommand(Player player) {
        super.giveCommand(player);

        return new MineCommand(player);
    }
}
