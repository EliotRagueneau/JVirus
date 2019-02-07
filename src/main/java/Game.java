public class Game {
    private static Map map;

    public static void main(String[] args) {
        map = new Map(20);
        map.show();
    }

    public static Map getMap() {
        return map;
    }
}
