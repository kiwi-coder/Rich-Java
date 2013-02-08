public abstract class Property {
    private Player owner;
    protected int price;
    private final String type;


    public Property(String type) {
        this.type = type;
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

    public abstract int getTollFee();

    public abstract int getSalePrice();

    public abstract Property upgrade();

    public Property reset() {
        Property land = new Land(getPrice());
        land.setOwner(null);

        return land;
    }

    public boolean hasOwner() {
        return owner != null;
    }
}