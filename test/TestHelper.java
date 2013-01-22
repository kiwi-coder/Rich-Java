public class TestHelper {
    public static String newLine() {
        return System.getProperty("line.separator");
    }

    public static Map simpleMap() {
        Map map = new Map(3, 3);
        for (int i = 0; i < map.size(); i++) map.setSite(i, new Site("0", i));
        return map;
    }
}
