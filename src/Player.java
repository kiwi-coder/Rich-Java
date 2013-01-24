public class Player {
    private String name;
    private int money;
    private Site site;
    private static final String[] names = new String[]{"QianFuRen", "ATuBo", "SunXiaoMei", "JinBeiBei"};

    public Player(String name, Site site, int money) {
        this.name = name;
        this.money = money;
        if (site != null) setPlayerOnSite(site);
    }

    public static Player createPlayer(char playerChar, Site site, int money) {
        int index = Integer.valueOf(String.valueOf(playerChar));
        return new Player(names[index - 1], site, money);
    }

    public static int playerSize() {
        return names.length;
    }

    public void forward(int steps) {
        int remainSteps = steps;
        while (remainSteps-- > 0) {
            forwardSingleStep();
        }
    }

    private void forwardSingleStep() {
        Site nextSite = site.nextSite();
        setSite(nextSite);
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

    public void setSite(Site targetSite) {
        if (site != null) site.resetPlayer();
        setPlayerOnSite(targetSite);
    }

    private void setPlayerOnSite(Site targetSite) {
        site = targetSite;
        targetSite.setPlayer(this);
    }

    public Site getSite() {
        return site;
    }

    public void buyProperty(){
        Property property = (Property)site;

        if(isPriceAffordable(property.getPrice())){
            pay(property.getPrice());
            property.setOwner(this);
        }
    }

    public void upgradeProperty(){
        Property property = (Property)site;

        if(isPriceAffordable(property.getPrice())){
            pay(property.getPrice());
            site = property.upgrade();
        }
    }

    private void pay(int price) {
        money -= price;
    }

    private boolean isPriceAffordable(int price){
        return money >= price;
    }

    public int getMoney() {
        return money;
    }
}
