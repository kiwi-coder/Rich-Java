package tw.rich.site;

import tw.rich.Player;
import tw.rich.command.Command;
import tw.rich.command.MineCommand;

public class PointMineSite extends Site {
    private static final String TYPE_CODE = "$";
    private int point;

    public PointMineSite(int point) {
        super(TYPE_CODE);
        this.point = point;
    }

    public int getPoint() {
        return point;
    }

    public Command giveCommand(Player player) {
        Command command = super.giveCommand(player);
        if(!player.isActive()) return command;

        return new MineCommand(player);
    }
}
