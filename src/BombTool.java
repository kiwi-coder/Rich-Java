public class BombTool extends Tool {
    public static final int BOMB_TOOL_CODE = 3;
    public static final int BOMB_TOOL_POINT = 50;
    private static final String CHINESE_NAME = "炸弹";

    public String display() {
        return "@";
    }

    public int getPoint() {
        return BOMB_TOOL_POINT;
    }

    @Override
    public int getToolCode() {
        return BOMB_TOOL_CODE;
    }

    public void usedOnSite(Site siteToPlaceBombTool) {
        siteToPlaceBombTool.setBombTool(this);
    }

    @Override
    public String getName() {
        return CHINESE_NAME;
    }
}
