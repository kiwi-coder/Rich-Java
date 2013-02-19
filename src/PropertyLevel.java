public abstract class PropertyLevel {
    private int price;
    private final String type;

    public PropertyLevel(String type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public abstract int getTollFee();

    public abstract int getSalePrice();

    public abstract PropertyLevel upgrade();

    public String display() {
        return type;
    }

    public String getType() {
        return type;
    }
}
