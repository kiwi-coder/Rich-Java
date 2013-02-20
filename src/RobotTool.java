public class RobotTool extends Tool {
    private static final int ROBOT_TOOL_RANGE = 10;
    public static final int ROBOT_TOOL_POINT = 30;
    public static final int ROBOT_TOOL_CODE = 2;
    private static final String CHINESE_NAME = "机器娃娃";
    public static final int ROBOT_USAGE_DISTANCE = 0;

    public String display() {
        return " ";
    }

    public int getPoint() {
        return ROBOT_TOOL_POINT;
    }

    @Override
    public int getToolCode() {
        return ROBOT_TOOL_CODE;
    }

    public void usedOnSite(Site siteToPlaceRobotTool) {
        int stepsLeft = ROBOT_TOOL_RANGE;
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
