import org.fusesource.jansi.AnsiConsole;

public class Rich {
    public static void main(String[] args) {
        if (isWindows()){
            AnsiConsole.systemInstall();
        }

        Game game = new Game(Map.RichMap());
        game.initPlayerMoney();
        game.selectPlayers();

        game.run();
    }

    private static boolean isWindows(){
        boolean result = false;
        if (System.getProperties().getProperty("os.name").toUpperCase().indexOf("WINDOWS") != -1) {
            result = true;
        }
        return result;
    }
}
