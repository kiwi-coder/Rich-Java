public class Rich {
    public static void main(String[] args) {
        Game game = new Game(getMap());
        game.initPlayerMoney();
        game.selectPlayers();
    }

    private static Map getMap() {
        Map map = new Map(29, 8);
        map.setSite(0, new Site("S"));
        for (int index = 1; index <= 13; index++) map.setSite(index, new Site("0"));
        map.setSite(14, new Site("H"));
        for (int index = 15; index <= 27; index++) map.setSite(index, new Site("0"));
        map.setSite(28, new Site("T"));
        for (int index = 29; index <= 34; index++) map.setSite(index, new Site("0"));
        map.setSite(35, new Site("G"));
        for (int index = 36; index <= 48; index++) map.setSite(index, new Site("0"));;
        map.setSite(49, new Site("P"));
        for (int index = 50; index <= 62; index++) map.setSite(index, new Site("0"));;
        map.setSite(63, new Site("M"));
        for (int index = 64; index <= 69; index++) map.setSite(index, new Site("$"));
        return map;
    }
}
