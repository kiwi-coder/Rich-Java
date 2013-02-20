import java.util.Scanner;

public class Site {
    protected String type;
    protected Player player;
    protected int index;
    protected Map map;
    private BlockTool blockTool;
    private BombTool bombTool;
    private Scanner scanner;

    public Site(String type) {
        this.type = type;
        this.index = 0;
        this.player = null;
        scanner = new Scanner(System.in);
    }

    private boolean hasPlayer() {
        return player != null;
    }

    public int getIndex() {
        return index;
    }

    public void setPlayer(Player player) {
        this.player = player;
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

    public void resetPlayer() {
        player = null;
    }

    public String display() {
        if (hasPlayer()) return player.display();
        return type;
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
        return new VoidCommand(player);
    }

    protected String prompt(String description) {
        System.out.println(description);
        return scanner.next();
    }
}
