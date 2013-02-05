import java.util.ArrayList;
import java.util.List;

public class Player {
    private final int MAX_TOO_NUMBER = 10;
    private String name;
    private int money;
    private Site site;
    private static final String[] names = new String[]{"QianFuRen", "ATuBo", "SunXiaoMei", "JinBeiBei"};
    private boolean isBroke = false;
    private int points;
    private List<Tool> tools = new ArrayList<Tool>();

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

    public void buyProperty() {
        Property property = (Property) site;

        if (isAffordable(property.getPrice())) {
            pay(property.getPrice());
            property.setOwner(this);
        }
    }

    public void upgradeProperty() {
        Property property = (Property) site;

        if (isPropertyUpgradable(property)) {
            pay(property.getPrice());
            site = property.upgrade();
        }
    }

    public boolean isPropertyUpgradable(Property property) {
        return isAffordable(property.getPrice()) && !(property instanceof Skyscraper);
    }

    private void pay(int price) {
        money -= price;
    }

    private boolean isAffordable(int price) {
        return money >= price;
    }

    public int getMoney() {
        return money;
    }

    public void payTollFee() {
        Property property = (Property) site;
        Player propertyOwner = property.getOwner();

        int tollFee = property.getTollFee();

        if (!isAffordable(tollFee)) {
            tollFee = money;
            broke();
        }

        payMoneyToPlayer(tollFee, propertyOwner);
    }

    public void payMoneyToPlayer(int money, Player player) {
        pay(money);
        player.earn(money);
    }

    private void broke() {
        isBroke = true;
    }

    private void earn(int money) {
        this.money += money;
    }

    public boolean isBroke() {
        return isBroke;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void buyTool(Tool tool) {
        if (points < tool.getPoint()) throw new PointsException();
        if (tools.size() >= MAX_TOO_NUMBER) throw new ToolException();
        points -= tool.getPoint();
        addTool(tool);
    }

    public int getToolsNumber() {
        return tools.size();
    }

    public void addTool(Tool tool) {
        tools.add(tool);
    }

    public int getPoints() {
        return points;
    }

    public void sellTool(Tool tool) {
        removeTool(tool);
        points += tool.getPoint();
    }

    private void removeTool(Tool toolToRemove) {
        for (Tool tool : tools) {
            if (tool.equals(toolToRemove)) {
                tools.remove(tool);
                return;
            }
        }
        throw new ToolException();
    }

    public int countTool(Tool toolToCount) {
        int result = 0;
        for (Tool tool : tools) {
            if (tool.equals(toolToCount)) result++;
        }
        return result;
    }
}
