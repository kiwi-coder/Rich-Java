public class RobotTool extends Tool {
    private static final int ROBOT_RANGE = 10;
    public static final int ROBOT_TOOL_CODE = 2;

    public String display() {
        return " ";
    }

    public int getPoint() {
        return 30;
    }

    @Override
    public int getToolCode() {
        return ROBOT_TOOL_CODE;
    }

    public void usedOnSite(Site siteToPlaceRobotTool) {
        int stepsLeft = ROBOT_RANGE;
        Site siteRobotOn = siteToPlaceRobotTool;

        while (stepsLeft-- > 0) {
            siteRobotOn = siteRobotOn.nextSite();
            siteRobotOn.clearTools();
        }
    }
}
