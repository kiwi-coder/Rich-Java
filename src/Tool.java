abstract class Tool {
    public abstract String display();

    public abstract int getPoint();

    public abstract int getToolCode();

    public boolean equals(Tool tool) {
        return display().equals(tool.display());
    }

    public boolean matchToolCode(int toolCode) {
        return getToolCode() == toolCode;
    }

    public static Tool makeTool(int toolCode){
        switch (toolCode){
            case BlockTool.BLOCK_TOOL_CODE:
                return new BlockTool();
            case BombTool.BOMB_TOOL_CODE:
                return new BombTool();
            case RobotTool.ROBOT_TOOL_CODE:
                return new RobotTool();
        }
        throw new ToolException();
    }

    public abstract void usedOnSite(Site siteToPlaceBombTool);
}
