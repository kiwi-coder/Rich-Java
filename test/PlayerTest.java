import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.StandardOutputStreamLog;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class PlayerTest {
    @Rule
    public final TextFromStandardInputStream systemInMock = TextFromStandardInputStream.emptyStandardInputStream();
    @Rule
    public final StandardOutputStreamLog systemOutMock = new StandardOutputStreamLog();

    private final Site DUMMY_SITE = null;
    private static final int DUMMY_MONEY = 0;
    private static final String DUMMY_NAME = "";
    private Player player;
    private Map map;
    private final int MAP_SIZE = TestHelper.simpleMap().size();
    private final int FIRST_SITE_INDEX = 0;
    private final int LAST_SITE_INDEX = MAP_SIZE - 1;

    @Before
    public void setUp() {
        map = TestHelper.simpleMap();
        player = new Player("ATuBo", DUMMY_SITE, DUMMY_MONEY);
    }

    @Test
    public void should_return_3_after_player_move_3_steps_from_0() {
        player.setSite(map.getSite(0));
        player.forward(3);
        assertThat(player.getSite(), is(map.getSite(3)));
    }

    @Test
    public void should_player_go_back_to_starting_site_from_last_site() {
        player.setSite(map.getSite(LAST_SITE_INDEX));
        player.forward(1);
        assertThat(player.getSite(), is(map.getSite(FIRST_SITE_INDEX)));
    }

    @Test
    public void should_return_A_for_player_name_is_ATuBo() {
        assertThat(player.display(), is("A"));
    }

    @Test
    public void should_player_buy_a_land_when_it_has_no_owner_and_he_has_enough_money() {
        // Given
        Property property = new Property(new Land(200));
        player.setMoney(5000);

        // When
        player.buyProperty(property);

        // Then
        assertThat(player.getMoney(), is(4800));
        assertThat(property.getOwner(), is(player));
    }

    @Test
    public void should_player_not_buy_a_land_when_he_has_no_enough_money() {
        // Given
        Property property = new Property(new Land(200));
        player.setMoney(100);

        // When
        player.buyProperty(property);

        // Then
        assertThat(player.getMoney(), is(100));
        assertFalse(property.hasOwner());
    }

    @Test
    public void should_player_upgrade_when_he_has_enough_money() {
        // Given
        player.setMoney(5000);
        Property property = new Property(new Land(200));
        property.setOwner(player);

        // When
        player.upgradeProperty(property);

        // Then
        assertThat(player.getMoney(), is(4800));
        assertThat(property.getLevel(), instanceOf(Cabin.class));
    }

    @Test
    public void should_not_player_upgrade_when_he_has_not_enough_money() {
        // Given
        player.setMoney(100);
        Property property = new Property(new Land(200));
        property.setOwner(player);

        // When
        player.upgradeProperty(property);

        // Then
        assertThat(player.getMoney(), is(100));
        assertThat(property.getLevel(), instanceOf(Land.class));
    }

    @Test
    public void should_player_pay_when_he_is_on_other_one_s_land_and_has_enough_money() {
        // Given
        Player atubo = new Player("Atubo", DUMMY_SITE, 5000);
        Player qianfuren = new Player("Qianfuren", DUMMY_SITE, 5000);

        Property landofQianfuren = new Property(new Land(200));
        landofQianfuren.setOwner(qianfuren);

        // When
        atubo.payTollFee(landofQianfuren);

        // Then
        assertThat(atubo.getMoney(), is(4900));
        assertThat(qianfuren.getMoney(), is(5100));
    }

    @Test
    public void should_player_broke_when_he_is_on_other_one_s_land_and_has_not_enough_money() {
        // Given
        Player atubo = new Player("Atubo", DUMMY_SITE, 50);
        Player qianfuren = new Player("Qianfuren", DUMMY_SITE, 5000);

        Property landofQianfuren = new Property(new Land(200));
        landofQianfuren.setOwner(qianfuren);

        // When
        atubo.payTollFee(landofQianfuren);

        // Then
        assertThat(atubo.isBroke(), is(true));
        assertThat(qianfuren.getMoney(), is(5050));
    }

    @Test
    public void test_player_selling_his_cabin() {
        // Given
        player.setMoney(5000);
        player.setSite(map.getSite(3));
        Property property = new Property(new Cabin(300));
        property.setOwner(player);
        map.setSite(0, property);

        // When
        player.sellPropertyByIndex(0);

        // Then
        assertFalse(property.hasOwner());
        assertThat(player.getMoney(), is(6200));
        assertThat(player.countProperty(Cabin.CABIN_TYPE_CODE), is(0));
        assertThat(property.getLevel(), instanceOf(Land.class));
    }

    @Test
    public void test_player_counting_his_properties() {
        // Given
        Property property = new Property(new House(300));
        property.setOwner(player);

        // When and then
        assertThat(player.countProperty("2"), is(1));
    }

    @Test
    public void should_success_buy_block_tool_when_player_has_enough_points() {
        player.setPoints(500);
        player.buyTool(BlockTool.BLOCK_TOOL_CODE);
        assertThat(player.getToolsNumber(), is(1));
        assertThat(player.getPoints(), is(450));
    }

    @Test(expected = PointsException.class)
    public void should_throw_exception_when_player_not_has_enough_points() {
        player.setPoints(0);
        player.buyTool(RobotTool.ROBOT_TOOL_CODE);
    }

    @Test(expected = ToolException.class)
    public void should_throw_exception_when_player_already_has_10_tools_and_cannot_buy_another() {
        for (int i = 0; i < 10; i++) player.addTool(new BombTool());
        player.setPoints(500);
        player.buyTool(BombTool.BOMB_TOOL_CODE);
    }

    @Test
    public void should_success_sell_tool_when_player_has_enough_points() {
        player.addTool(new BombTool());
        player.sellTool(BombTool.BOMB_TOOL_CODE);
        assertThat(player.getPoints(), is(50));
        assertThat(player.getToolsNumber(), is(0));
    }

    @Test(expected = ToolNotFoundException.class)
    public void should_failed_sell_tool_when_player_do_not_have_that_kind_of_tool() {
        player.sellTool(BombTool.BOMB_TOOL_CODE);
    }

    @Test
    public void should_return_3_when_count_bomb_tool() {
        for (int i = 0; i < 3; i++) player.addTool(new BombTool());
        player.addTool(new BlockTool());
        player.addTool(new RobotTool());
        assertThat(player.countTool(BombTool.BOMB_TOOL_CODE), is(3));
    }

    @Test
    public void should_player_earn_2000_money_when_he_chose_money_at_gift_house() {
        // Given
        player.setMoney(5000);

        // When
        player.chooseGift(MoneyGift.MONEY_GIFT_CODE);

        // Then
        assertThat(player.getMoney(), is(7000));
    }

    @Test
    public void should_player_earn_200_point_when_he_chose_point_at_gift_house() {
        // Given
        player.setPoints(200);

        // When
        player.chooseGift(PointGift.POINT_GIFT_CODE);

        // Then
        assertThat(player.getPoints(), is(400));
    }

    @Test
    public void should_player_get_a_god_of_luck_carried_on_when_he_chose_it_at_gift_house() {
        // Given
        player.setGodOfLuck(null);

        // When
        player.chooseGift(GodOfLuckGift.GOD_OF_LUCK_GIFT_CODE);

        // Then
        assertTrue(player.hasGodOfLuck());
    }

    @Test
    public void should_not_player_pay_toll_fee_when_he_has_a_god_of_luck() {
        //TODO: 我感觉这个应该是更上层的逻辑, 应该写在 Game 里面.
    }

    @Test
    public void should_god_of_luck_leave_the_player_after_five_rounds() {
        // Given
        player.setGodOfLuck(new GodOfLuck());

        // When
        RoundEngine.instance().nextRound();
        RoundEngine.instance().nextRound();
        RoundEngine.instance().nextRound();
        RoundEngine.instance().nextRound();
        RoundEngine.instance().nextRound();

        // Then
        assertFalse(player.hasGodOfLuck());
    }

    @Test
    public void should_player_get_correct_amount_of_points_when_he_is_on_point_mine() {
        // Given
        player.setPoints(200);
        PointMineSite pointMineSite = new PointMineSite(80);

        // When
        player.mine(pointMineSite);

        // Then
        assertThat(player.getPoints(), is(280));
    }

    @Test
    public void test_player_at_0_placing_block_tool_on_1_site_forward() {
        // Given
        player.addTool(new BlockTool());
        player.setSite(map.getSite(0));

        // When
        player.useTool(BlockTool.BLOCK_TOOL_CODE, 1);

        // Then
        assertTrue(map.getSite(1).hasBlockTool());
        assertThat(player.countTool(BlockTool.BLOCK_TOOL_CODE), is(0));
    }

    @Test
    public void test_player_at_last_site_placing_block_tool_on_1_site_forward() {
        // Given
        player.addTool(new BlockTool());
        player.setSite(map.getSite(LAST_SITE_INDEX));

        // When
        player.useTool(BlockTool.BLOCK_TOOL_CODE, 1);

        // Then
        assertTrue(map.getSite(0).hasBlockTool());
        assertThat(player.countTool(BlockTool.BLOCK_TOOL_CODE), is(0));
    }

    @Test
    public void test_player_at_1_placing_block_tool_on_1_site_afterward() {
        // Given
        player.addTool(new BlockTool());
        player.setSite(map.getSite(1));

        // When
        player.useTool(BlockTool.BLOCK_TOOL_CODE, -1);

        // Then
        assertTrue(map.getSite(0).hasBlockTool());
        assertThat(player.countTool(BlockTool.BLOCK_TOOL_CODE), is(0));
    }

    @Test
    public void test_player_at_0_placing_block_tool_on_1_site_afterward() {
        // Given
        player.addTool(new BlockTool());
        player.setSite(map.getSite(0));

        // When
        player.useTool(BlockTool.BLOCK_TOOL_CODE, -1);

        // Then
        assertTrue(map.getSite(LAST_SITE_INDEX).hasBlockTool());
        assertThat(player.countTool(BlockTool.BLOCK_TOOL_CODE), is(0));
    }

    @Test
    public void test_player_at_1_placing_block_tool_on_site_1() {
        // Given
        player.addTool(new BlockTool());
        player.setSite(map.getSite(1));

        // When
        player.useTool(BlockTool.BLOCK_TOOL_CODE, 0);

        // Then
        assertTrue(map.getSite(1).hasBlockTool());
        assertThat(player.countTool(BlockTool.BLOCK_TOOL_CODE), is(0));
    }

    @Test
    public void should_player_stop_at_1_because_of_the_block_tool_when_he_was_supposed_to_move_to_3() {
        // Given
        player.setSite(map.getSite(0));
        map.getSite(1).setBlockTool(new BlockTool());

        // When
        player.forward(3);

        // Then
        assertThat(player.getSite(), is(map.getSite(1)));
    }

    @Test
    public void test_player_at_1_placing_bomb_tool_on_site_2() {
        // Given
        player.addTool(new BombTool());
        player.setSite(map.getSite(1));

        // When
        player.useTool(BombTool.BOMB_TOOL_CODE, 1);

        // Then
        assertTrue(map.getSite(2).hasBombTool());
        assertThat(player.countTool(BombTool.BOMB_TOOL_CODE), is(0));
    }

    @Test
    public void test_player_stepping_on_bomb_and_sent_to_hospital() {
        // Given
        player.setSite(map.getSite(0));
        map.getSite(2).setBombTool(new BombTool());
        map.setSite(5, new HospitalSite());

        // When
        player.forward(2);

        // Then
        assertThat(player.getSite(), is(map.getSite(5)));
        assertFalse(player.isMovable());
    }

    @Test
    public void should_not_player_be_movable_one_round_after_sent_to_hospital() {
        // Given
        map.setSite(5, new HospitalSite());
        player.setSite(map.getSite(0));
        player.sentToHospital();

        // When
        RoundEngine.instance().nextRound();

        // Then
        assertFalse(player.isMovable());
    }

    @Test
    public void should_player_be_movable_three_round_after_sent_to_hospital() {
        // Given
        map.setSite(5, new HospitalSite());
        player.setSite(map.getSite(0));
        player.sentToHospital();

        // When
        RoundEngine.instance().nextRound();
        RoundEngine.instance().nextRound();
        RoundEngine.instance().nextRound();

        // Then
        assertTrue(player.isMovable());
    }

    @Test
    public void test_player_at_1_placing_robot_tool_on_site_2() {
        // Given
        player.addTool(new RobotTool());
        player.setSite(map.getSite(1));
        map.getSite(3).setBlockTool(new BlockTool());
        map.getSite(4).setBombTool(new BombTool());

        // When
        player.useTool(RobotTool.ROBOT_TOOL_CODE, 0);

        // Then
        assertFalse(map.getSite(3).hasBlockTool());
        assertFalse(map.getSite(4).hasBombTool());
        assertThat(player.countTool(RobotTool.ROBOT_TOOL_CODE), is(0));
    }

    @Test
    public void should_not_player_be_movable_after_staying_at_prison_for_1_rounds() {
        // Given
        player.setSite(new PrisonSite());

        // When
        RoundEngine.instance().nextRound();

        // Then
        assertFalse(player.isMovable());
    }

    @Test
    public void should_player_be_movable_after_staying_at_prison_for_2_rounds() {
        // Given
        player.setSite(new PrisonSite());

        // When
        RoundEngine.instance().nextRound();
        RoundEngine.instance().nextRound();

        // Then
        assertTrue(player.isMovable());
    }

    @Test
    public void test_player_executing_roll_command() {
        // Given
        player.setSite(map.getSite(0));
        Command command = Command.makeCommand("roll", player);
        Site oldSite = player.getSite();

        // When
        player.executeCommand(command);

        // Then
        assertFalse(oldSite == player.getSite());
    }

    @Test
    public void test_player_executing_block_command() {
        // Given
        player.setSite(map.getSite(0));
        player.addTool(new BlockTool());
        Command command = Command.makeCommand("block 3", player);

        // When
        player.executeCommand(command);

        // Then
        assertTrue(map.getSite(3).hasBlockTool());
    }

    @Test
    public void test_player_executing_bomb_command() {
        // Given
        player.setSite(map.getSite(0));
        player.addTool(new BombTool());
        Command command = Command.makeCommand("bomb 3", player);

        // When
        player.executeCommand(command);

        // Then
        assertTrue(map.getSite(3).hasBombTool());
    }

    @Test
    public void test_player_executing_robot_command() {
        // Given
        player.setSite(map.getSite(0));
        player.addTool(new RobotTool());
        Command command = Command.makeCommand("robot", player);

        map.getSite(3).setBlockTool(new BlockTool());
        map.getSite(4).setBombTool(new BombTool());

        // When
        player.executeCommand(command);

        // Then
        assertFalse(map.getSite(3).hasBlockTool());
        assertFalse(map.getSite(4).hasBombTool());
    }

    @Test
    public void test_player_executing_selling_property_command() {
        // Given
        Property property = new Property(new House(400));
        property.setOwner(player);
        map.setSite(0, property);
        player.setMoney(5000);
        player.setSite(map.getSite(7));

        Command command = Command.makeCommand("sell 0", player);

        // When
        player.executeCommand(command);

        // Then
        assertFalse(property.hasOwner());
        assertThat(player.getMoney(), is(7400));
        assertThat(property.getLevel(), instanceOf(Land.class));
    }

    @Test
    public void test_player_executing_selling_tool_command() {
        // Given
        player.addTool(new BlockTool());
        player.setPoints(500);
        Command command = Command.makeCommand("sellTool 1", player);

        // When
        player.executeCommand(command);

        // Then
        assertThat(player.getPoints(), is(550));
        assertThat(player.countTool(BlockTool.BLOCK_TOOL_CODE), is(0));
    }

    @Test
    public void test_player_executing_query_command() {
        // Given
        player.setMoney(5400);
        player.setPoints(300);
        for (int i = 0; i < 3; i++)
            player.addProperty(new Property(new Land(300)));
        player.addProperty(new Property(new Skyscraper(300)));
        player.addProperty(new Property(new House(300)));
        player.addProperty(new Property(new Cabin(300)));
        player.addTool(new BombTool());
        player.addTool(new BlockTool());
        player.addTool(new RobotTool());

        Command command = Command.makeCommand("query", player);

        // When
        player.executeCommand(command);

        // Then
        String expectedString = "资金：5400 元\n" +
                "点数：300 点\n" +
                "地产：空地 3 处；茅屋 1 处；洋房 1 处；摩天楼 1 处。\n" +
                "道具：路障 1 个；炸弹 1 个；机器娃娃 1 个\n";
        assertEquals(expectedString, systemOutMock.getLog());
    }

    @Test
    public void test_player_executing_help_command() {
        // Given
        Command command = Command.makeCommand("help", player);

        // When
        player.executeCommand(command);

        // Then
        String expectedString = "命令一览表\n" +
                "roll:\t掷骰子命令，行走1~6步。步数由随即算法产生。\n" +
                "block n:\t玩家拥有路障后，可将路障放置到离当前位置前后10步的距离，任一玩家经过路障，都将被拦截。" +
                "该道具一次有效。n 前后的相对距离，负数表示后方。\n" +
                "bomb n:\t可将路障放置到离当前位置前后10步的距离，任一玩家j 经过在该位置，将被炸伤，送往医院，住院三天。" +
                "n 前后的相对距离，负数表示后方。\n" +
                "robot:\t使用该道具，可清扫前方路面上10步以内的其它道具，如炸弹、路障。\n" +
                "sell x:\t出售自己的房产，x 地图上的绝对位置，即地产的编号。\n" +
                "sellTool x\t出售道具，x 道具编号\n" +
                "query:\t显示自家资产信息\n" +
                "help:\t查看命令帮助\n" +
                "quit:\t强制退出\n";

        assertEquals(expectedString, systemOutMock.getLog());
    }

    @Test
    public void test_player_stopping_on_land_after_moving_and_get_asked_if_he_wants_to_buy() {
        // Given
        player.setMoney(5000);
        Property property = new Property(new Land(300));
        map.setSite(0, property);
        player.setSite(map.getSite(0));

        // When
        systemInMock.provideText("Y");
        property.giveCommand(player);


        // Then
        String expectedString = "是否购买该处空地，300 元（Y/N）?\n";
        assertEquals(expectedString, systemOutMock.getLog());
    }

    @Test
    public void test_player_stopping_on_land_after_moving_and_he_pressed_Y_and_buy_the_land() {
        // Given
        player.setMoney(5000);
        player.setSite(map.getSite(0));
        Property property = new Property(new Land(300));
        map.setSite(3, property);

        // When
        systemInMock.provideText("Y");
        player.forward(3);

        // Then
        assertThat(property.getOwner(), is(player));
    }

    @Test
    public void test_player_stopping_on_land_after_moving_and_he_pressed_N_and_not_buy_the_land() {
        // Given
        player.setMoney(5000);
        player.setSite(map.getSite(0));
        Property property = new Property(new Land(300));
        map.setSite(3, property);

        // When
        systemInMock.provideText("N");
        player.forward(3);

        // Then
        assertFalse(property.hasOwner());
    }

    @Test
    public void test_player_stopping_on_his_land_after_moving_and_get_asked_if_he_wants_to_upgrade() {
        // Given
        player.setMoney(5000);
        Property property = new Property(new Land(300));
        property.setOwner(player);
        map.setSite(0, property);
        player.setSite(map.getSite(0));

        // When
        systemInMock.provideText("Y");
        property.giveCommand(player);

        // Then
        String expectedString = "是否升级该处地产，300 元（Y/N）?\n";
        assertEquals(expectedString, systemOutMock.getLog());
    }

    @Test
    public void test_player_stopping_on_his_land_after_moving_and_he_pressed_Y_and_upgraded_the_land() {
        // Given
        player.setMoney(5000);
        player.setSite(map.getSite(0));
        Property property = new Property(new Land(300));
        property.setOwner(player);
        map.setSite(3, property);

        // When
        systemInMock.provideText("Y");
        player.forward(3);

        // Then
        assertThat(property.getType(), is(Cabin.CABIN_TYPE_CODE));
    }

    @Test
    public void test_player_stopping_on_his_land_after_moving_and_he_pressed_N_and_not_upgraded_the_land() {
        // Given
        player.setMoney(5000);
        player.setSite(map.getSite(0));
        Property property = new Property(new Land(300));
        property.setOwner(player);
        map.setSite(3, property);

        // When
        systemInMock.provideText("N");
        player.forward(3);

        // Then
        assertThat(property.getType(), is(Land.LAND_TYPE_CODE));
    }

    @Test
    public void test_player_stopping_on_other_player_s_property() {
        // Given
        player.setMoney(5000);
        player.setSite(map.getSite(0));
        Player other = new Player("Qianfuren", null, 5000);

        Property property = new Property(new House(300));
        property.setOwner(other);
        map.setSite(3, property);

        // Then
        player.forward(3);

        // Then
        assertThat(player.getMoney(), is(4400));
        assertThat(other.getMoney(), is(5600));
    }

    @Test
    public void test_player_who_has_a_god_of_luck_stopping_on_other_player_s_property() {
        // Given
        player.setMoney(5000);
        player.setSite(map.getSite(0));
        player.setGodOfLuck(new GodOfLuck());
        Player other = new Player("Qianfuren", null, 5000);

        Property property = new Property(new House(300));
        property.setOwner(other);
        map.setSite(3, property);

        // When
        player.forward(3);

        // Then
        assertThat(player.getMoney(), is(5000));
        assertThat(other.getMoney(), is(5000));
        String expectedString = "福神附身，可免过路费\n";
        assertEquals(expectedString, systemOutMock.getLog());
    }

    @Test
    public void test_player_stopping_on_gift_house(){
        // Given
        GiftHouseSite giftHouseSite = new GiftHouseSite();
        map.setSite(3, giftHouseSite);
        player.setSite(map.getSite(0));
        player.setPoints(200);

        // When
        systemInMock.provideText("2");
        player.forward(3);

        // Then
        assertThat(player.getPoints(), is(400));
    }
}