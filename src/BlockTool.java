public class BlockTool extends Tool {
    public static final int BLOCK_TOOL_CODE = 1;

    public String display() {
        return "#";
    }

    public int getPoint() {
        return 50;
    }

    @Override
    public int getToolCode() {
        return BLOCK_TOOL_CODE;
    }

    public void usedOnSite(Site siteToHaveBlockTool) {
        siteToHaveBlockTool.setBlockTool(this);
    }
}