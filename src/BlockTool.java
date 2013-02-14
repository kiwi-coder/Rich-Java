public class BlockTool extends Tool {
    public String display() {
        return "#";
    }

    public int getPoint() {
        return 50;
    }

    public void usedOnSite(Site siteToHaveBlockTool) {
        siteToHaveBlockTool.setBlockTool(this);
    }
}