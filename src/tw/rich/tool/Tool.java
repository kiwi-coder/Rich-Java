package tw.rich.tool;

import tw.rich.exception.ToolException;
import tw.rich.site.Site;

public abstract class Tool {
    public abstract String display();

    public abstract int getPoint();

    public abstract int getToolCode();

    public boolean matchToolCode(int toolCode) {
        return getToolCode() == toolCode;
    }

    public static Tool makeTool(int toolCode) {
        switch (toolCode) {
            case BlockTool.TOOL_CODE:
                return new BlockTool();
            case BombTool.TOOL_CODE:
                return new BombTool();
            case RobotTool.TOOL_CODE:
                return new RobotTool();
        }
        throw new ToolException();
    }

    public abstract void usedOnSite(Site siteToPlaceBombTool);

    public abstract String getName();

    public static Tool makeCheapestTool() {
        return new RobotTool();
    }
}
