package tw.rich.command;

import tw.rich.Player;
import tw.rich.tool.Tool;

public class BuyToolCommand extends Command {
    private int toolCode;

    public BuyToolCommand(Player player, int toolCode) {
        super();
        this.player = player;
        this.toolCode = toolCode;
    }

    @Override
    public void execute() {
        Tool tool = Tool.makeTool(toolCode);

        if (player.getToolsNumber() >= Player.MAX_TOOL_NUMBER) return;

        if (player.canAffordTool(tool)) {
            player.buyTool(tool);
        } else {
            System.out.println("您当前剩余的点数为 " + player.getPoints() + "， 不足以购买" + tool.getName() + "道具");
        }
    }
}
