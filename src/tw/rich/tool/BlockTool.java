package tw.rich.tool;

import tw.rich.site.Site;

public class BlockTool extends Tool {
    public static final int BLOCK_TOOL_CODE = 1;
    public static final int BLOCK_TOOL_POINT = 50;
    private static final String CHINESE_NAME = "路障";

    public String display() {
        return "#";
    }

    public int getPoint() {
        return BLOCK_TOOL_POINT;
    }

    @Override
    public int getToolCode() {
        return BLOCK_TOOL_CODE;
    }

    public void usedOnSite(Site siteToHaveBlockTool) {
        siteToHaveBlockTool.setBlockTool(this);
    }

    @Override
    public String getName() {
        return CHINESE_NAME;
    }
}