package tw.rich;

import tw.rich.site.Site;

public class MapLayout {
    private int width;
    private int height;
    private final String[][] layout;

    public MapLayout(int width, int height) {
        this.width = width;
        this.height = height;
        this.layout = new String[height][width];
        initLayout();
    }

    private void initLayout() {
        for (int height = 0; height < getHeight(); height++) {
            for (int width = 0; width < getWidth(); width++) {
                layout[height][width] = " ";
            }
        }
    }

    public String display(Map map) {
        assert (map.size() == size());
        for (int index = 0; index < map.size(); index++) {
            Site site = map.getSite(index);
            setCellDisplay(getHeightOfSite(site), getWidthOfSite(site), site);
        }
        return linkCellDisplay();
    }

    private int getWidthOfSite(Site site) {
        int index = site.getIndex();
        if (isTop(index)) return index;
        if (isRight(index)) return getWidth() - 1;
        if (isBottom(index)) return 2 * getWidth() + getHeight() - 3 - index;
        else return 0;
    }

    private int getHeightOfSite(Site site) {
        int index = site.getIndex();
        if (isTop(index)) return 0;
        if (isRight(index)) return index - getWidth() + 1;
        if (isBottom(index)) return getHeight() - 1;
        else return size() - index;
    }

    private int size() {
        return 2 * (width + height) - 4;
    }

    private void setCellDisplay(int height, int width, Site site) {
        layout[height][width] = site.display();
    }

    private String linkCellDisplay() {
        String result = "";
        for (int height = 0; height < getHeight(); height++) {
            for (int width = 0; width < getWidth(); width++) {
                result += layout[height][width];
            }
            result += "\n";
        }
        return result;
    }

    private boolean isBottom(int index) {
        return index >= getWidth() + getHeight() - 2 && index <= getWidth() * 2 + getHeight() - 3;
    }

    private boolean isRight(int index) {
        return index >= getWidth() - 1 && index <= getWidth() + getHeight() - 2;
    }

    private boolean isTop(int index) {
        return index < getWidth();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
