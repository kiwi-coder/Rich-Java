import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private final int DEFAULT_INIT_MONEY = 10000;
    private final int MIN_INIT_MONEY = 1000;
    private final int MAX_INIT_MONEY = 50000;
    private int initPlayerMoney = DEFAULT_INIT_MONEY;
    private Scanner scanner;
    private ArrayList<Player> players;
    private Map map;

    public Game(Map map) {
        scanner = new Scanner(System.in);
        players = new ArrayList();
        this.map = map;
    }

    public void initPlayerMoney() {
        try {
            String moneyString = prompt("设置玩家初始资金，范围1000～50000（默认10000）");
            setInitPlayerMoney(Integer.parseInt(moneyString));
        } catch (Exception exception) {
            System.err.println("输入玩家初始资金错误，请再次输入");
            initPlayerMoney();
        }
    }

    private String prompt(String description) {
        System.out.println(description);
        return scanner.next();
    }

    private boolean initMoneyOutOfRange(int initPlayerMoney) {
        return initPlayerMoney < MIN_INIT_MONEY || initPlayerMoney > MAX_INIT_MONEY;
    }

    public int getInitPlayerMoney() {
        return initPlayerMoney;
    }

    public void setInitPlayerMoney(int initPlayerMoney) {
        if (initMoneyOutOfRange(initPlayerMoney)) throw new IllegalUserInputException();
        this.initPlayerMoney = initPlayerMoney;
    }

    public void selectPlayers() {
        try {
            String playerString = prompt("请选择2~4位不重复玩家，输入编号即可。(1.钱夫人; 2.阿土伯; 3.孙小美; 4.金贝贝):");
            if (hasPlayerNumberError(playerString)) throw new IllegalUserInputException();
            if (hasDuplicatePlayers(playerString)) throw new IllegalUserInputException();
            for (char playerChar : playerString.toCharArray()) {
                players.add(selectPlayer(playerChar));
            }
        } catch (Exception exception) {
            System.err.println("选择玩家错误，请再次选择");
            selectPlayers();
        }
    }

    private Player selectPlayer(char playerChar) {
        Player player = new Player(map);
        setPlayerName(player, playerChar);
        player.setMoney(initPlayerMoney);
        return player;
    }

    private boolean hasPlayerNumberError(String playerString) {
        return playerString.length() < 2 || playerString.length() > 4;
    }

    private boolean hasDuplicatePlayers(String playerString) {
        char[] playerChars = playerString.toCharArray();
        for (char playChar : playerChars) {
            if (playerString.indexOf(playChar) != playerString.lastIndexOf(playChar)) return true;
        }
        return false;
    }

    private void setPlayerName(Player player, char playerChar) {
        String name = "";
        if (playerChar == '1') {
            name = "QianFuRen";
        } else if (playerChar == '2') {
            name = "ATuBo";
        } else if (playerChar == '3') {
            name = "SunXiaoMei";
        } else if (playerChar == '4') {
            name = "JinBeiBei";
        } else {
            throw new IllegalUserInputException();
        }
        player.setName(name);
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }
}
