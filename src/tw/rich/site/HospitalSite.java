package tw.rich.site;

public class HospitalSite extends Site {
    private static final String HOSPITAL_TYPE_CODE = "H";
    public static final int ROUND_TO_STAY_FOR_INJURED_PLAYER = 3;

    public HospitalSite() {
        super(HOSPITAL_TYPE_CODE);
    }
}
