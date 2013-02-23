package tw.rich.site;

public class PrisonSite extends Site {
    private static final String TYPE_CODE = "P";
    public static final int ROUND_TO_STAY_IN_PRISON = 2;

    public PrisonSite() {
        super(TYPE_CODE);
    }
}
