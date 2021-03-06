import org.junit.Test;
import tw.rich.Color;
import tw.rich.tool.BlockTool;
import tw.rich.tool.BombTool;
import tw.rich.tool.RobotTool;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PointTest {
    @Test
    public void should_display_sharp_for_block_tool() {
        BlockTool blockTool = new BlockTool();
        String blackPound = Color.paint(Color.ANSI_BLACK, "#");
        assertThat(blockTool.display(), is(blackPound));
    }

    @Test
    public void should_return_50_for_block_tool_points() {
        BlockTool blockTool = new BlockTool();
        assertThat(blockTool.getPoint(), is(50));
    }

    @Test
    public void should_display_empty_for_robot_tool() {
        RobotTool robotTool = new RobotTool();
        assertThat(robotTool.display(), is(" "));
    }

    @Test
    public void should_return_30_for_robot_tool() {
        RobotTool robotTool = new RobotTool();
        assertThat(robotTool.getPoint(), is(30));
    }

    @Test
    public void should_display_at_symbol_for_bomb_tool() {
        BombTool bombTool = new BombTool();
        String blackAt = Color.paint(Color.ANSI_BLACK, "@");
        assertThat(bombTool.display(), is(blackAt));
    }

    @Test
    public void should_return_50_for_bomb_tool() {
        BombTool bombTool = new BombTool();
        assertThat(bombTool.getPoint(), is(50));
    }
}
