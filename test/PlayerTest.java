import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PlayerTest {
    private Player player;
    private Map map;
    private final int MAP_SIZE = TestHelper.simpleMap().size();
    private final int START_POSITION = 0;
    private final int LAST_POSITION = MAP_SIZE - 1;

    @Before
    public void setUp() {
        map = TestHelper.simpleMap();
        player = new Player(map);
    }

    @Test
    public void should_return_3_after_player_move_3_steps_from_0() {
        player.setPosition(0);
        player.move(3);
        assertThat(player.getPosition(), is(3));
    }

    @Test
    public void should_player_go_back_to_starting_position_from_last_position() {
        player.setPosition(LAST_POSITION);
        player.move(1);
        assertThat(player.getPosition(), is(START_POSITION));
    }

    @Test
    public void should_return_A_for_player_name_is_ATuBo() {
        player.setName("AToBo");
        assertThat(player.display(), is("A"));
    }
}