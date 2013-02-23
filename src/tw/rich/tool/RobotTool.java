package tw.rich.tool;

import tw.rich.site.Site;

public class RobotTool extends Tool {
    private static final int TOOL_RANGE_IN_EFFECT = 10;
    public static final int TOOL_POINT = 30;
    public static final int TOOL_CODE = 2;
    private static final String CHINESE_NAME = "机器娃娃";
    public static final int USAGE_DISTANCE = 0;

    public String display() {
        /*TODO: This func should be made private..., no need to display robot tool*/
        return " ";
    }

    public int getPoint() {
        return TOOL_POINT;
    }

    @Override
    public int getToolCode() {
        return TOOL_CODE;
    }

    public void usedOnSite(Site siteToPlaceRobotTool) {
        int stepsLeft = TOOL_RANGE_IN_EFFECT;
        Site siteRobotOn = siteToPlaceRobotTool;

        while (stepsLeft-- > 0) {
            siteRobotOn = siteRobotOn.nextSite();
            siteRobotOn.clearTools();
        }
    }

    @Override
    public String getName() {
        return CHINESE_NAME;
    }
}
