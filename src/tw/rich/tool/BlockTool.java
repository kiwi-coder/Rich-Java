package tw.rich.tool;

import tw.rich.Color;
import tw.rich.site.Site;

public class BlockTool extends Tool {
    public static final int TOOL_CODE = 1;
    public static final int TOOL_POINT = 50;
    private static final String CHINESE_NAME = "路障";
    public static final int RANGE = 10;

    public String display() {
        return Color.paint(Color.ANSI_BLACK, "#");
    }

    public int getPoint() {
        return TOOL_POINT;
    }

    @Override
    public int getToolCode() {
        return TOOL_CODE;
    }

    public void usedOnSite(Site siteToHaveBlockTool) {
        siteToHaveBlockTool.setBlockTool(this);
    }

    @Override
    public String getName() {
        return CHINESE_NAME;
    }
}