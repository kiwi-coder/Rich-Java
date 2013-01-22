public class Player {
    private String name;
    private Map map;
    private int money;
    private Site site;

    public Player(Map map) {
        this.map = map;
    }

    public void forward(int steps) {
        int newSiteIndex = (site.getIndex() + steps) % map.size();
        setSite(new Site("0", this, newSiteIndex));
    }

    public int getSiteIndex() {
        if (site == null) throw new IllegalPlayerException();
        return site.getIndex();
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

    public void setSite(Site site) {
        this.site = site;
    }
}
