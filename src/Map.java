import java.util.*;

public class Map {
    private int width;
    private int height;
    private List<Site> sites = new ArrayList();

    public Map(int width, int height) {
        this.width = width;
        this.height = height;
        for (int index = 0; index < size(); index++) sites.add(null);
    }

    public int size() {
        return 2 * (width + height) - 4;
    }

    public void setSite(int index, Site site) {
        sites.set(index, site);
        site.setMap(this);
    }

    public String display() {
        StringBuilder[] lines = initEmptyDisplayMap();
        displaySites(lines);
        return concatenate(lines);
    }

    private void setSiteDisplay(StringBuilder[] lines, int siteIndex, String display) {
        lines[getHeight(siteIndex)].setCharAt(getWidth(siteIndex), display.charAt(0));
    }

    private void displaySites(StringBuilder[] lines) {
        for (int index = 0; index < size(); index++) {
            setSiteDisplay(lines, index, sites.get(index).toString());
        }
    }

    private StringBuilder[] initEmptyDisplayMap() {
        StringBuilder[] lines = new StringBuilder[height];
        for (int line = 0; line < lines.length; line++) lines[line] = new StringBuilder(createSpacesLine());
        return lines;
    }

    private String concatenate(StringBuilder[] lines) {
        StringBuilder result = new StringBuilder();
        for (StringBuilder line : lines) result.append(line);
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
        for (int i = 0; i < width; i++) result.append(" ");
        result.append("\n");
        return result.toString();
    }

    public Site getSite(int index) {
        return sites.get(index);
    }
}