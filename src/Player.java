public class Player {
    private String name;
    private int money;
    private Site site;

    public Player(Site site, String name) {
        setSite(site);
        this.name = name;
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

    public void setMoney(int money) {
        this.money = money;
    }

    public void setSite(Site site) {
        if (this.site != null) this.site.resetPlayer();
        this.site = site;
        site.setPlayer(this);
    }

    public Site getSite() {
        return site;
    }
}
