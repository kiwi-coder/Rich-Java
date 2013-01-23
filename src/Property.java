public class Property extends Site{
    private Player owner;

    public Property(String type) {
        super(type);
    }

    public Site upgrade(){
        return null;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player player) {
        owner = player;
    }
}
