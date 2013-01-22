public class Site {
    private String type;
    private Player player;
    private int index;

    public Site(String type, int index){
        this.type = type;
        this.index = index;
        this.player = null;
    }

    @Override
    public String toString() {
        if (hasPlayer()) return player.display();
        return type;
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
}
