class Property extends Site{
    private PropertyLevel level;
    private Player owner = null;

    public Property(PropertyLevel level) {
        super(level.getType());
        this.level = level;
    }

    public void upgrade() {
        level = level.upgrade();
    }

    public PropertyLevel getLevel() {
        return level;
    }

    public int getSalePrice() {
        return level.getSalePrice();
    }

    public int getTollFee() {
        return level.getTollFee();
    }

    public int getPrice() {
        return level.getPrice();
    }

    public Player getOwner() {
        return owner;
    }

    public void reset() {
        level = new Land(getPrice());
        owner = null;
    }

    public boolean hasOwner() {
        return owner != null;
    }

    public void setOwner(Player player) {
        owner = player;
    }

    public String display() {
        return level.display();
    }
}