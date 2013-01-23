public class Player {
    private String name;
    private int money;
    private Site site;

    public Player(String name, Site site, int money) {
        if (site != null) setPlayerOnSite(site);
        this.name = name;
        this.money = money;
    }

    public static Player createPlayer(char playerChar, Site site, int money) {
        int index = Integer.valueOf(String.valueOf(playerChar));
        String[] names = {"QianFuRen", "ATuBo", "SunXiaoMei", "JinBeiBei"};
        return new Player(names[index - 1], site, money);
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
}
