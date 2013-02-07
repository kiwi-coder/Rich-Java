import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

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
        Property property = new Land();
        Player player = new Player("Atubo", property, 5000);
        property.setPlayer(player);
        property.setPrice(200);

        // When
        player.buyProperty();

        // Then
        assertThat(player.getMoney(), is(4800));
        assertThat(player.getSite().getType(), is("0"));
        assertThat(((Property) player.getSite()).getOwner().getName(), is("Atubo"));
    }

    @Test
    public void should_player_not_buy_a_land_when_he_has_no_enough_money() {
        // Given
        Property property = new Land();
        Player player = new Player("Atubo", property, 180);
        property.setPlayer(player);
        property.setPrice(200);

        // When
        player.buyProperty();

        // Then
        assertThat(player.getMoney(), is(180));
        assertThat(player.getSite().getType(), is("0"));
        assertTrue(((Property) player.getSite()).getOwner() == null);
    }

    @Test
    public void should_player_upgrade_his_land_to_cabin_when_he_has_enough_money() {
        // Given
        Property property = new Land();
        Player player = new Player("Atubo", property, 5000);
        property.setPlayer(player);
        property.setPrice(200);
        property.setOwner(player);
        property.setMap(map);

        // When
        player.upgradeProperty();

        // Then
        assertThat(player.getMoney(), is(4800));
        assertThat(player.getSite().getType(), is("1"));
    }

    @Test
    public void should_not_player_upgrade_his_land_to_cabin_when_he_has_not_enough_money() {
        // Given
        Property property = new Land();
        Player player = new Player("Atubo", property, 180);
        property.setPlayer(player);
        property.setPrice(200);
        property.setOwner(player);
        property.setMap(map);

        // When
        player.upgradeProperty();

        // Then
        assertThat(player.getMoney(), is(180));
        assertThat(player.getSite().getType(), is("0"));
    }

    @Test
    public void should_player_upgrade_its_cabin_to_house_when_he_has_enough_money() {
        // Given
        Property property = new Cabin();
        Player player = new Player("Atubo", property, 5000);
        property.setPlayer(player);
        property.setPrice(200);
        property.setOwner(player);
        property.setMap(map);

        // When
        player.upgradeProperty();

        // Then
        assertThat(player.getMoney(), is(4800));
        assertThat(player.getSite().getType(), is("2"));
    }

    @Test
    public void should_not_player_upgrade_his_cabin_to_house_when_has_not_enough_money() {
        // Given
        Property property = new Cabin();
        Player player = new Player("Atubo", property, 180);
        property.setPlayer(player);
        property.setPrice(200);
        property.setOwner(player);
        property.setMap(map);

        // When
        player.upgradeProperty();

        // Then
        assertThat(player.getMoney(), is(180));
        assertThat(player.getSite().getType(), is("1"));
    }

    @Test
    public void should_player_upgrade_its_house_to_skyscraper_when_he_has_enough_money() {
        // Given
        Property property = new House();
        Player player = new Player("Atubo", property, 5000);
        property.setPlayer(player);
        property.setPrice(200);
        property.setOwner(player);
        property.setMap(map);

        // When
        player.upgradeProperty();

        // Then
        assertThat(player.getMoney(), is(4800));
        assertThat(player.getSite().getType(), is("3"));
    }

    @Test
    public void should_not_player_upgrade_his_house_to_skyscraper_when_has_not_enough_money() {
        // Given
        Property property = new House();
        Player player = new Player("Atubo", property, 180);
        property.setPlayer(player);
        property.setPrice(200);
        property.setOwner(player);
        property.setMap(map);

        // When
        player.upgradeProperty();

        // Then
        assertThat(player.getMoney(), is(180));
        assertThat(player.getSite().getType(), is("2"));
    }

    @Test
    public void should_not_player_upgrade_its_skyscraper_when_he_has_enough_money() {
        // Given
        Property property = new Skyscraper();
        Player player = new Player("Atubo", property, 5000);
        property.setPlayer(player);
        property.setPrice(200);
        property.setOwner(player);
        property.setMap(map);

        // When
        player.upgradeProperty();

        // Then
        assertThat(player.getMoney(), is(5000));
        assertThat(player.getSite().getType(), is("3"));
    }

    @Test
    public void should_not_player_upgrade_its_skyscraper_when_he_has_not_enough_money() {
        // Given
        Property property = new Skyscraper();
        Player player = new Player("Atubo", property, 180);
        property.setPlayer(player);
        property.setPrice(200);
        property.setOwner(player);
        property.setMap(map);

        // When
        player.upgradeProperty();

        // Then
        assertThat(player.getMoney(), is(180));
        assertThat(player.getSite().getType(), is("3"));
    }

    @Test
    public void should_player_pay_when_he_is_on_other_one_s_land_and_has_enough_money() {
        // Given
        Property landofQianfuren = new Land();
        Property randomProperty = new Land();
        Player atubo = new Player("Atubo", landofQianfuren, 5000);
        Player qianfuren = new Player("Qianfuren", randomProperty, 5000);

        landofQianfuren.setPlayer(atubo);
        landofQianfuren.setPrice(200);
        landofQianfuren.setOwner(qianfuren);

        // When
        atubo.payTollFee();

        // Then
        assertThat(atubo.getMoney(), is(4900));
        assertThat(qianfuren.getMoney(), is(5100));
    }

    @Test
    public void should_player_broke_when_he_is_on_other_one_s_land_and_has_not_enough_money() {
        // Given
        Property landofQianfuren = new Land();
        Property randomProperty = new Land();
        Player atubo = new Player("Atubo", landofQianfuren, 50);
        Player qianfuren = new Player("Qianfuren", randomProperty, 5000);

        landofQianfuren.setPlayer(atubo);
        landofQianfuren.setPrice(200);
        landofQianfuren.setOwner(qianfuren);

        // When
        atubo.payTollFee();

        // Then
        assertThat(atubo.isBroke(), is(true));
        assertThat(qianfuren.getMoney(), is(5050));
    }

    @Test
    public void should_player_pay_when_he_is_on_other_one_s_cabin_and_has_enough_money() {
        // Given
        Property landofQianfuren = new Cabin();
        Property randomProperty = new Land();
        Player atubo = new Player("Atubo", landofQianfuren, 5000);
        Player qianfuren = new Player("Qianfuren", randomProperty, 5000);

        landofQianfuren.setPlayer(atubo);
        landofQianfuren.setPrice(300);
        landofQianfuren.setOwner(qianfuren);

        // When
        atubo.payTollFee();

        // Then
        assertThat(atubo.getMoney(), is(4700));
        assertThat(qianfuren.getMoney(), is(5300));
    }

    @Test
    public void should_player_broke_when_he_is_on_other_one_s_cabin_and_has_not_enough_money() {
        // Given
        Property landofQianfuren = new Cabin();
        Property randomProperty = new Land();
        Player atubo = new Player("Atubo", landofQianfuren, 50);
        Player qianfuren = new Player("Qianfuren", randomProperty, 5000);

        landofQianfuren.setPlayer(atubo);
        landofQianfuren.setPrice(300);
        landofQianfuren.setOwner(qianfuren);

        // When
        atubo.payTollFee();

        // Then
        assertThat(atubo.isBroke(), is(true));
        assertThat(qianfuren.getMoney(), is(5050));
    }

    @Test
    public void should_player_pay_when_he_is_on_other_one_s_house_and_has_enough_money() {
        // Given
        Property landofQianfuren = new House();
        Property randomProperty = new Land();
        Player atubo = new Player("Atubo", landofQianfuren, 5000);
        Player qianfuren = new Player("Qianfuren", randomProperty, 5000);

        landofQianfuren.setPlayer(atubo);
        landofQianfuren.setPrice(300);
        landofQianfuren.setOwner(qianfuren);

        // When
        atubo.payTollFee();

        // Then
        assertThat(atubo.getMoney(), is(4400));
        assertThat(qianfuren.getMoney(), is(5600));
    }

    @Test
    public void should_player_broke_when_he_is_on_other_one_s_house_and_has_not_enough_money() {
        // Given
        Property landofQianfuren = new House();
        Property randomProperty = new Land();
        Player atubo = new Player("Atubo", landofQianfuren, 50);
        Player qianfuren = new Player("Qianfuren", randomProperty, 5000);

        landofQianfuren.setPlayer(atubo);
        landofQianfuren.setPrice(300);
        landofQianfuren.setOwner(qianfuren);

        // When
        atubo.payTollFee();

        // Then
        assertThat(atubo.isBroke(), is(true));
        assertThat(qianfuren.getMoney(), is(5050));
    }

    @Test
    public void should_player_pay_when_he_is_on_other_one_s_skyscraper_and_has_enough_money() {
        // Given
        Property landofQianfuren = new Skyscraper();
        Property randomProperty = new Land();
        Player atubo = new Player("Atubo", landofQianfuren, 5000);
        Player qianfuren = new Player("Qianfuren", randomProperty, 5000);

        landofQianfuren.setPlayer(atubo);
        landofQianfuren.setPrice(300);
        landofQianfuren.setOwner(qianfuren);

        // When
        atubo.payTollFee();

        // Then
        assertThat(atubo.getMoney(), is(3800));
        assertThat(qianfuren.getMoney(), is(6200));
    }

    @Test
    public void should_player_broke_when_he_is_on_other_one_s_skyscraper_and_has_not_enough_money() {
        // Given
        Property landofQianfuren = new Skyscraper();
        Property randomProperty = new Land();
        Player atubo = new Player("Atubo", landofQianfuren, 50);
        Player qianfuren = new Player("Qianfuren", randomProperty, 5000);

        landofQianfuren.setPlayer(atubo);
        landofQianfuren.setPrice(300);
        landofQianfuren.setOwner(qianfuren);

        // When
        atubo.payTollFee();

        // Then
        assertThat(atubo.isBroke(), is(true));
        assertThat(qianfuren.getMoney(), is(5050));
    }

    @Test
    public void test_player_selling_his_land(){
        // Given
        player.setMoney(5000);
        Property property = new Land();

        property.setPrice(300);
        property.setOwner(player);
        property.setMap(map);

        // When
        property = player.sellProperty(property);

        // Then
        assertTrue(property.getOwner() == null);
        assertTrue(property instanceof Land);
        assertThat(player.getMoney(), is(5600));
    }

    @Test
    public void test_player_selling_his_cabin(){
        // Given
        player.setMoney(5000);
        Property property = new Cabin();

        property.setPrice(300);
        property.setOwner(player);
        property.setMap(map);

        // When
        property = player.sellProperty(property);

        // Then
        assertTrue(property.getOwner() == null);
        assertTrue(property instanceof Land);
        assertThat(player.getMoney(), is(6200));
    }

    @Test
    public void test_player_selling_his_house(){
        // Given
        player.setMoney(5000);
        Property property = new House();

        property.setPrice(300);
        property.setOwner(player);
        property.setMap(map);

        // When
        property = player.sellProperty(property);

        // Then
        assertTrue(property.getOwner() == null);
        assertTrue(property instanceof Land);
        assertThat(player.getMoney(), is(6800));
    }

    @Test
    public void test_player_selling_his_skyscraper(){
        // Given
        player.setMoney(5000);
        Property property = new Skyscraper();

        property.setPrice(300);
        property.setOwner(player);
        property.setMap(map);

        // When
        property = player.sellProperty(property);

        // Then
        assertTrue(property.getOwner() == null);
        assertTrue(property instanceof Land);
        assertThat(player.getMoney(), is(7400));
    }

    @Test
    public void should_success_buy_player_when_player_has_enough_points() {
        player.setPoints(500);
        player.buyTool(new BlockTool());
        assertThat(player.getToolsNumber(), is(1));
        assertThat(player.getPoints(), is(450));
    }

    @Test(expected = PointsException.class)
    public void should_throw_exception_when_player_not_has_enough_points() {
        player.setPoints(0);
        player.buyTool(new RobotTool());
    }

    @Test(expected = ToolException.class)
    public void should_throw_exception_when_player_already_has_10_tools_and_cannot_buy_another() {
        for (int i = 0; i < 10; i++) player.addTool(new BombTool());
        player.setPoints(500);
        player.buyTool(new BombTool());
    }

    @Test
    public void should_success_sell_tool_when_player_has_enough_tool() {
        player.addTool(new BombTool());
        player.sellTool(new BombTool());
        assertThat(player.getPoints(), is(50));
        assertThat(player.getToolsNumber(), is(0));
    }

    @Test(expected = ToolException.class)
    public void should_failed_sell_tool_when_player_do_not_have_that_kind_of_tool() {
        player.sellTool(new BombTool());
    }

    @Test
    public void should_return_3_when_count_bomb_tool() {
        for (int i = 0; i < 3; i++) player.addTool(new BombTool());
        player.addTool(new BlockTool());
        player.addTool(new RobotTool());
        assertThat(player.countTool(new BombTool()), is(3));
    }

    @Test
    public void should_player_earn_2000_money_when_he_chose_money_at_gift_house(){
        // Given
        player.setMoney(5000);

        // When
        player.chooseMoneyAtGiftHouse();

        // Then
        assertThat(player.getMoney(), is(7000));
    }

    @Test
    public void should_player_earn_200_point_when_he_chose_point_at_gift_house(){
        // Given
        player.setPoints(200);

        // When
        player.choosePointAtGiftHouse();

        // Then
        assertThat(player.getPoints(), is(400));
    }

    @Test
    public void should_player_get_a_god_of_luck_carried_on_when_he_chose_it_at_gift_house(){
        // Given
        player.setGodOfLuck(null);

        // When
        player.chooseGodOfLuckAtGiftHouse();

        // Then
        assertTrue( player.hasGodOfLuck());
    }

    @Test
    public void should_not_player_pay_toll_fee_when_he_has_a_god_of_luck(){
        //TODO: 我感觉这个应该是更上层的逻辑, 应该写在 Game 里面.
    }

    @Test
    public void should_god_of_luck_leave_the_player_after_five_rounds(){
        // TODO: 我感觉这个应该是更上层的逻辑, 应该写在 Game 里面.
    }

}