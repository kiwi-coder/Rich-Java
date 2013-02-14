public class BombTool extends Tool {
    public String display() {
        return "@";
    }

    public int getPoint() {
        return 50;
    }

    public void usedOnSite(Site siteToPlaceBombTool) {
        siteToPlaceBombTool.setBombTool(this);
    }
}
