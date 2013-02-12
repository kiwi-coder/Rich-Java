public class PointMine extends Site {
    private static final String POINT_MINE_TYPE_CODE = "$";
    private int point;

    public PointMine(int point) {
        super(POINT_MINE_TYPE_CODE);
        this.point = point;
    }

    public int getPoint() {
        return point;
    }
}
