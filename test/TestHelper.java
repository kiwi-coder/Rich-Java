import tw.rich.Map;
import tw.rich.MapLayout;
import tw.rich.site.Site;

import java.util.ArrayList;
import java.util.List;

public class TestHelper {
    public static String newLine() {
        return System.getProperty("line.separator");
    }

    public static Map simpleMap() {
        List<Site> sites = new ArrayList();
        for (int index = 0; index < 8; index++) {
            sites.add(new Site("0"));
        }
        return new Map(sites, new MapLayout(3, 3));
    }

}
