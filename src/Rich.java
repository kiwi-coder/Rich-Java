public class Rich {
    public static void main(String[] args) {
        Game game = new Game(getMap());
        game.initPlayerMoney();
        game.selectPlayers();
    }

    private static Map getMap() {
        Map map = new Map(29, 8);
        map.setPositionDisplay(0, "S");
        for (int index = 1; index <= 13; index++) map.setPositionDisplay(index, "0");
        map.setPositionDisplay(14, "H");
        for (int index = 15; index <= 27; index++) map.setPositionDisplay(index, "0");
        map.setPositionDisplay(28, "T");
        for (int index = 29; index <= 34; index++) map.setPositionDisplay(index, "0");
        map.setPositionDisplay(35, "G");
        for (int index = 36; index <= 48; index++) map.setPositionDisplay(index, "0");
        map.setPositionDisplay(49, "P");
        for (int index = 50; index <= 62; index++) map.setPositionDisplay(index, "0");
        map.setPositionDisplay(63, "M");
        for (int index = 64; index <= 69; index++) map.setPositionDisplay(index, "$");
        return map;
    }
}
