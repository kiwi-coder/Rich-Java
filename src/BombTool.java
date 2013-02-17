public class BombTool extends Tool {
    public static final int BOMB_TOOL_CODE = 3;

    public String display() {
        return "@";
    }

    public int getPoint() {
        return 50;
    }

    @Override
    public int getToolCode() {
        return BOMB_TOOL_CODE;
    }

    public void usedOnSite(Site siteToPlaceBombTool) {
        siteToPlaceBombTool.setBombTool(this);
    }
}
