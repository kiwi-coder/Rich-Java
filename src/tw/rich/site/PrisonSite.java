package tw.rich.site;

public class PrisonSite extends Site {
    private static final String PRISON_TYPE_CODE = "P";
    public static final int ROUND_TO_STAY_IN_PRISON = 2;

    public PrisonSite() {
        super(PRISON_TYPE_CODE);
    }
}
