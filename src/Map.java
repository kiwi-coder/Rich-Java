import java.util.*;

public class Map {
    private int width;
    private int height;
    private ArrayList<String> positions = new ArrayList();
    private ArrayList<Player> players = new ArrayList();

    public Map(int width, int height) {
        this.width = width;
        this.height = height;
        for (int i = 0; i < size(); i++) positions.add(" ");
    }

    public int size() {
        return 2 * (width + height) - 4;
    }

    public void setPositionDisplay(int index, String display) {
        positions.set(index, display);
    }

    public String display() {
        StringBuilder[] lines = initEmptyDisplayMap();
        displayPositions(lines);
        displayPlayers(lines);
        return concatenate(lines);
    }

    private void displayPlayers(StringBuilder[] lines) {
        for (Player player : players) {
            lines[getHeight(player.getPosition())].setCharAt(getWidth(player.getPosition()), player.display().charAt(0));
        }
    }

    private void displayPositions(StringBuilder[] lines) {
        for (int index = 0; index < size(); index++) {
            lines[getHeight(index)].setCharAt(getWidth(index), positions.get(index).charAt(0));
        }
    }

    private StringBuilder[] initEmptyDisplayMap() {
        StringBuilder[] lines = new StringBuilder[height];
        for (int line = 0; line < lines.length; line++) lines[line] = new StringBuilder(createSpacesLine());
        return lines;
    }

    private String concatenate(StringBuilder[] lines) {
        StringBuilder result = new StringBuilder();
        for (int line = 0; line < lines.length; line++) result.append(lines[line]);
        return result.toString();
    }

    private int getWidth(int index) {
        if (isTop(index)) return index;
        if (isRight(index)) return width - 1;
        if (isBottom(index)) return 2 * width + height - 3 - index;
        else return 0;
    }

    private int getHeight(int index) {
        if (isTop(index)) return 0;
        if (isRight(index)) return index - width + 1;
        if (isBottom(index)) return height - 1;
        else return size() - index;
    }

    private boolean isBottom(int index) {
        return index >= width + height - 2 && index <= width * 2 + height - 3;
    }

    private boolean isRight(int index) {
        return index >= width - 1 && index <= width + height - 2;
    }

    private boolean isTop(int index) {
        return index < width;
    }

    private String createSpacesLine() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; isTop(i); i++) result.append(" ");
        result.append("\n");
        return result.toString();
    }

    public void addPlayer(Player player) {
        players.add(player);
    }
}
