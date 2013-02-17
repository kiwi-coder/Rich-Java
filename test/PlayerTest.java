import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class PlayerTest {
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
        Property property = new Property(new Cabin(300));
        property.setOwner(player);

        // When
        player.sellProperty(property);

        // Then
        assertFalse(property.hasOwner());
        assertThat(player.getMoney(), is(6200));
        assertThat(property.getLevel(), instanceOf(Land.class));
    }

    @Test
    public void should_success_buy_player_when_player_has_enough_points() {
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
        player.chooseMoneyAtGiftHouse();

        // Then
        assertThat(player.getMoney(), is(7000));
    }

    @Test
    public void should_player_earn_200_point_when_he_chose_point_at_gift_house() {
        // Given
        player.setPoints(200);

        // When
        player.choosePointAtGiftHouse();

        // Then
        assertThat(player.getPoints(), is(400));
    }

    @Test
    public void should_player_get_a_god_of_luck_carried_on_when_he_chose_it_at_gift_house() {
        // Given
        player.setGodOfLuck(null);

        // When
        player.chooseGodOfLuckAtGiftHouse();

        // Then
        assertTrue(player.hasGodOfLuck());
    }

    @Test
    public void should_not_player_pay_toll_fee_when_he_has_a_god_of_luck() {
        //TODO: 我感觉这个应该是更上层的逻辑, 应该写在 Game 里面.
    }

    @Test
    public void should_god_of_luck_leave_the_player_after_five_rounds() {
        // TODO: 我感觉这个应该是更上层的逻辑, 应该写在 Game 里面.
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
    public void test_player_at_0_placing_block_tool_on_1_site_forward(){
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
    public void test_player_at_last_site_placing_block_tool_on_1_site_forward(){
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
    public void test_player_at_1_placing_block_tool_on_1_site_afterward(){
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
    public void test_player_at_0_placing_block_tool_on_1_site_afterward(){
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
    public void test_player_at_1_placing_block_tool_on_site_1(){
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
    public void should_player_stop_at_1_because_of_the_block_tool_when_he_was_supposed_to_move_to_3(){
        // Given
        player.setSite(map.getSite(0));
        map.getSite(1).setBlockTool(new BlockTool());

        // When
        player.forward(3);

        // Then
        assertThat(player.getSite(), is(map.getSite(1)));
    }

    @Test
    public void test_player_at_1_placing_bomb_tool_on_site_2(){
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
    public void test_player_stepping_on_bomb_and_sent_to_hospital(){
        // TODO:...
    }

    @Test
    public void test_player_at_1_placing_robot_tool_on_site_2(){
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
}