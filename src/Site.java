public class Site {
    private String type;
    private Player player;
    private int index;

    public Site(String type) {
        this.type = type;
        this.player = null;
        this.index = 0;
    }

    public Site(String type, int index){
        this.type = type;
        this.index = index;
    }

    public Site(String type, Player player, int index){
        this.type = type;
        this.player = player;
        this.index = index;
    }

    @Override
    public String toString() {
        if (player != null) return player.display();
        return type;
    }

    public int getIndex(){
        return index;
    }
}
