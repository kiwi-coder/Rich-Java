public class Property extends Site{
    private Player owner;
    protected int price;

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getTollFee() {
        return 0;
    }

    public int getSalePrice() {
        return 0;
    }

    public Property reset() {
        Property land = new Land(getPrice());
        land.setOwner(null);

        return land;
    }

    public boolean hasOwner() {
        return owner != null;
    }
}
