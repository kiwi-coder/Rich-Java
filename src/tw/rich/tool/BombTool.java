package tw.rich.tool;

import tw.rich.Color;
import tw.rich.site.Site;

public class BombTool extends Tool {
    public static final int TOOL_CODE = 3;
    public static final int TOOL_POINT = 50;
    private static final String CHINESE_NAME = "炸弹";

    public String display() {
        return Color.paint(Color.ANSI_BLACK, "@");
    }

    public int getPoint() {
        return TOOL_POINT;
    }

    @Override
    public int getToolCode() {
        return TOOL_CODE;
    }

    public void usedOnSite(Site siteToPlaceBombTool) {
        siteToPlaceBombTool.setBombTool(this);
    }

    @Override
    public String getName() {
        return CHINESE_NAME;
    }
}
