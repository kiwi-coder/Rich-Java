public class Player {
    private int position;
    private String name;
    private Map map;
    private int money;

    public Player(Map map) {
        this.map = map;
        map.addPlayer(this);
    }

    public void move(int steps) {
        position += steps;
        position %= map.size();
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String display() {
        return getFirstCharacterOfName();
    }

    private String getFirstCharacterOfName() {
        return String.valueOf(name.charAt(0)).toUpperCase();
    }

    public String getName() {
        return name;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
