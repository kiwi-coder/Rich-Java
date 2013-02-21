public class Rich {
    public static void main(String[] args) {
        Game game = new Game(Map.RichMap());
        game.initPlayerMoney();
        game.selectPlayers();

        game.run();
    }
}
