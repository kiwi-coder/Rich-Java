public class Site {
    private String type;
    private Player player;
    private int index;
    private Map map;

    public Site(String type, int index){
        this.type = type;
        this.index = index;
        this.player = null;
    }

    public Site(String type) {
        this(type, 0);
    }

    private boolean hasPlayer() {
        return player != null;
    }

    public int getIndex(){
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
}
