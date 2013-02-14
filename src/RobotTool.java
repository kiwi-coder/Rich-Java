public class RobotTool extends Tool {
    private static final int ROBOT_RANGE = 10;
    public String display() {
        return " ";
    }

    public int getPoint() {
        return 30;
    }

    public void usedOnSite(Site siteToPlaceRobotTool) {
        int stepsLeft = ROBOT_RANGE;
        Site siteRobotOn = siteToPlaceRobotTool;

        while(stepsLeft-- > 0){
            siteRobotOn = siteRobotOn.nextSite();
            siteRobotOn.clearTools();
        }
    }
}
