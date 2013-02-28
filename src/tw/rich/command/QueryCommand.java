package tw.rich.command;

import tw.rich.Player;
import tw.rich.site.property.Cabin;
import tw.rich.site.property.House;
import tw.rich.site.property.Land;
import tw.rich.site.property.Skyscraper;
import tw.rich.tool.BlockTool;
import tw.rich.tool.BombTool;
import tw.rich.tool.RobotTool;

public class QueryCommand extends Command {
    public QueryCommand(Player player) {
        super();
        this.player = player;
    }

    @Override
    public void execute() {
        String format = "资金：%d 元\n" +
                "点数：%d 点\n" +
                "地产：空地 %d 处；茅屋 %d 处；洋房 %d 处；摩天楼 %d 处。\n" +
                "道具：路障 %d 个；炸弹 %d 个；机器娃娃 %d 个\n";
        String output = String.format(format,
                player.getMoney(),
                player.getPoints(),
                player.countProperty(Land.TYPE_CODE), player.countProperty(Cabin.TYPE_CODE),
                player.countProperty(House.TYPE_CODE), player.countProperty(Skyscraper.TYPE_CODE),
                player.countTool(BlockTool.TOOL_CODE), player.countTool(BombTool.TOOL_CODE),
                player.countTool(RobotTool.TOOL_CODE));
        System.out.print(output);
    }
}
