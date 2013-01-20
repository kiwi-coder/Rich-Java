public class Player {
    private int siteIndex;
    private String name;
    private Map map;
    private int money;

    public Player(Map map) {
        this.map = map;
        map.addPlayer(this);
    }

    public void forward(int steps) {
        siteIndex += steps;
        siteIndex %= map.size();
    }

    public int getSiteIndex() {
        return siteIndex;
    }

    public void setSiteIndex(int siteIndex) {
        this.siteIndex = siteIndex;
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
