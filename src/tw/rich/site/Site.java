package tw.rich.site;

import tw.rich.Color;
import tw.rich.Map;
import tw.rich.Player;
import tw.rich.command.Command;
import tw.rich.command.DoNothingCommand;
import tw.rich.exception.HospitalNotFoundException;
import tw.rich.tool.BlockTool;
import tw.rich.tool.BombTool;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Site {
    protected String type;
    protected List<Player> players = new ArrayList();
    protected int index;
    protected Map map;
    protected BlockTool blockTool;
    protected BombTool bombTool;
    private Scanner scanner;

    public Site(String type) {
        this.type = type;
        this.index = 0;
        scanner = new Scanner(System.in);
    }

    protected boolean hasPlayer() {
        return players.size() != 0;
    }

    public int getIndex() {
        return index;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public Site nextSite() {
        return map.getSite(nextIndex());
    }

    private int nextIndex() {
        return (index + 1) % map.size();
    }

    public String display() {
        if (hasPlayer()) return players.get(players.size() - 1).display();
        if (hasBlockTool()) return blockTool.display();
        if (hasBombTool()) return bombTool.display();

        return Color.paint(Color.ANSI_BLACK, type);
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public boolean hasBlockTool() {
        return blockTool != null;
    }


    public void setBlockTool(BlockTool blockTool) {
        this.blockTool = blockTool;
    }

    public Site previousSite() {
        return map.getSite(previousIndex());
    }

    private int previousIndex() {
        return (index - 1 + map.size()) % map.size();
    }

    public boolean hasBombTool() {
        return bombTool != null;
    }

    public void setBombTool(BombTool bombTool) {
        this.bombTool = bombTool;
    }

    public void clearTools() {
        bombTool = null;
        blockTool = null;
    }

    public HospitalSite findNearestHospital() {
        Site forward = this;
        Site backward = this;
        for (int i = 0; i < map.size() / 2; i++) {
            if (forward.nextSite() instanceof HospitalSite)
                return (HospitalSite) forward.nextSite();
            if (backward.previousSite() instanceof HospitalSite)
                return (HospitalSite) backward.previousSite();

            forward = forward.nextSite();
            backward = backward.previousSite();
        }

        throw new HospitalNotFoundException();
    }

    public Command giveCommand(Player player) {
        if (hasBombTool()) player.sentToHospital();
        return new DoNothingCommand(player);
    }

    protected String prompt(String description) {
        System.out.println(description);
        return scanner.next();
    }

    private Player findPlayer(Player playerToFind) {
        for (Player player : players) {
            if (player.equals(playerToFind))
                return player;
        }
        return null;
    }

    public void removePlayer(Player player) {
        players.remove(findPlayer(player));
    }
}
