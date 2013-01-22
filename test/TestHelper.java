public class TestHelper {
    public static String newLine() {
        return System.getProperty("line.separator");
    }

    public static Map simpleMap() {
        Map map = new Map(3, 3);
        for (int index = 0; index < map.size(); index++) map.setSite(index, new Site("0", index));
        return map;
    }
}
