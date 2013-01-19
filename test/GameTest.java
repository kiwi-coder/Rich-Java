import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.StandardErrorStreamLog;
import org.junit.contrib.java.lang.system.StandardOutputStreamLog;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class GameTest {

    private static final String DUMMY_MONEY_STRING = "10000";
    private Game game;

    @Rule
    public final TextFromStandardInputStream systemInMock = TextFromStandardInputStream.emptyStandardInputStream();
    @Rule
    public final StandardOutputStreamLog systemOutMock = new StandardOutputStreamLog();
    @Rule
    public final StandardErrorStreamLog systemErrMock = new StandardErrorStreamLog();

    @Before
    public void setUp() throws Exception {
        game = new Game();
    }

    @Test
    public void should_output_init_notice_message() {
        String expected_notice = "设置玩家初始资金，范围1000～50000（默认10000）" + TestHelper.newLine();
        systemInMock.provideText(DUMMY_MONEY_STRING);
        game.initPlayerMoney();
        assertEquals(expected_notice, systemOutMock.getLog());
    }

    @Test
    public void should_return_1000_for_player_init_money_when_input_is_1000() {
        systemInMock.provideText("1000");
        game.initPlayerMoney();
        assertEquals(1000, game.getInitPlayerMoney());
    }

    @Test
    public void should_has_another_change_to_init_player_money_if_input_is_invalid() {
        systemInMock.provideText("INVALID_INPUT" + TestHelper.newLine() + "2000");
        game.initPlayerMoney();
        assertEquals(2000, game.getInitPlayerMoney());
        assertEquals("输入玩家初始资金错误，请再次输入" + TestHelper.newLine(), systemErrMock.getLog());
    }

    @Test
    public void should_has_another_change_to_init_player_money_when_input_is_out_of_range() {
        systemInMock.provideText("0" + TestHelper.newLine() + "10000");
        game.initPlayerMoney();
        assertEquals(10000, game.getInitPlayerMoney());
        assertEquals("输入玩家初始资金错误，请再次输入" + TestHelper.newLine(), systemErrMock.getLog());
    }

    @Test
    public void should_output_select_players_notice_message() {
        String expected_notice = "请选择2~4位不重复玩家，输入编号即可。(1.钱夫人; 2.阿土伯; 3.孙小美; 4.金贝贝):" + TestHelper.newLine();
        systemInMock.provideText("1234");
        game.selectPlayers();
        assertEquals(expected_notice, systemOutMock.getLog());
    }

    @Test
    public void should_select_QianFuRen_ATuBo_SunXiaoMei_JinBeiBei() {
        systemInMock.provideText("1234");
        game.selectPlayers();
        List<Player> players = game.getPlayers();
        assertEquals(4, players.size());
        assertEquals("QianFuRen", players.get(0).getName());
        assertEquals("ATuBo", players.get(1).getName());
        assertEquals("SunXiaoMei", players.get(2).getName());
        assertEquals("JinBeiBei", players.get(3).getName());
    }

    @Test
    public void should_has_another_chance_to_select_players_if_player_index_is_invalid() {
        String expected_notice = "请选择2~4位不重复玩家，输入编号即可。(1.钱夫人; 2.阿土伯; 3.孙小美; 4.金贝贝):" + TestHelper.newLine()
                + "请选择2~4位不重复玩家，输入编号即可。(1.钱夫人; 2.阿土伯; 3.孙小美; 4.金贝贝):" + TestHelper.newLine();
        systemInMock.provideText("1235" + TestHelper.newLine() + "1234");
        game.selectPlayers();
        assertEquals(expected_notice, systemOutMock.getLog());
        assertEquals("选择玩家错误，请再次选择" + TestHelper.newLine(), systemErrMock.getLog());
    }

    @Test
    public void should_has_another_chance_to_select_players_if_player_size_is_only_one() {
        String expected_notice = "请选择2~4位不重复玩家，输入编号即可。(1.钱夫人; 2.阿土伯; 3.孙小美; 4.金贝贝):" + TestHelper.newLine()
                + "请选择2~4位不重复玩家，输入编号即可。(1.钱夫人; 2.阿土伯; 3.孙小美; 4.金贝贝):" + TestHelper.newLine();
        systemInMock.provideText("1" + TestHelper.newLine() + "1234");
        game.selectPlayers();
        assertEquals(expected_notice, systemOutMock.getLog());
        assertEquals("选择玩家错误，请再次选择" + TestHelper.newLine(), systemErrMock.getLog());
    }

    @Test
    public void should_has_another_chance_to_select_players_if_player_is_duplicate() {
        String expected_notice = "请选择2~4位不重复玩家，输入编号即可。(1.钱夫人; 2.阿土伯; 3.孙小美; 4.金贝贝):" + TestHelper.newLine()
                + "请选择2~4位不重复玩家，输入编号即可。(1.钱夫人; 2.阿土伯; 3.孙小美; 4.金贝贝):" + TestHelper.newLine();
        systemInMock.provideText("1111" + TestHelper.newLine() + "1234");
        game.selectPlayers();
        assertEquals(expected_notice, systemOutMock.getLog());
        assertEquals("选择玩家错误，请再次选择" + TestHelper.newLine(), systemErrMock.getLog());
    }
}
