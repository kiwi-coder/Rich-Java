package tw.rich;

import tw.rich.exception.IllegalUserInputException;
import tw.rich.site.Site;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    private final int DEFAULT_INIT_MONEY = 10000;
    private final int MIN_INIT_MONEY = 1000;
    private final int MAX_INIT_MONEY = 50000;
    private final int MIN_PLAYER_NUMBER = 2;
    private final int MAX_PLAYER_NUMBER = Player.playerSize();

    private int initPlayerMoney = DEFAULT_INIT_MONEY;
    private Scanner scanner = new Scanner(System.in);
    private List<Player> players = new ArrayList();
    private Map map;

    public Game(Map map) {
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

    public String prompt(String description) {
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
            if (isPlayerNumberOutOfRange(playerString)) throw new IllegalUserInputException();
            if (hasDuplicatePlayers(playerString)) throw new IllegalUserInputException();
            for (char playerChar : playerString.toCharArray()) {
                addPlayer(Player.createPlayer(playerChar, firstSite(), initPlayerMoney));
            }
        } catch (Exception exception) {
            System.err.println("选择玩家错误，请再次选择");
            selectPlayers();
        }
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public Site firstSite() {
        return map.getSite(0);
    }

    private boolean isPlayerNumberOutOfRange(String playerString) {
        return playerString.length() < MIN_PLAYER_NUMBER || playerString.length() > MAX_PLAYER_NUMBER;
    }

    private boolean hasDuplicatePlayers(String playerString) {
        char[] playerChars = playerString.toCharArray();
        for (char playChar : playerChars) {
            if (playerString.indexOf(playChar) != playerString.lastIndexOf(playChar)) return true;
        }
        return false;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public boolean isOver() {
        int playerNumber = 0;
        for (Player player : players) {
            if (player.isBroke()) continue;
            playerNumber++;
        }
        return playerNumber == 1;
    }

    public Player winner() {
        if (isOver()) return players.get(0);
        return null;
    }

    public void run() {
        while (true) {
            for (Player player : players) {
                if (!player.isBroke()) {
                    System.out.println(map.display());
                    player.takeTurn();
                }
            }
            RoundEngine.instance().nextRound();
        }
    }
}
