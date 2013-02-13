public class PointMineSite extends Site {
    private static final String POINT_MINE_TYPE_CODE = "$";
    private int point;

    public PointMineSite(int point) {
        super(POINT_MINE_TYPE_CODE);
        this.point = point;
    }

    public int getPoint() {
        return point;
    }
}