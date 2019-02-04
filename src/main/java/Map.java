import java.util.Random;

public class Map {
    private static Content[][] map = new Empty[20][20];

    public Map() {
        for (int x = 0; x < 20; x++) {
            for (int y = 0; y < 20; y++) {
                map[x][y] = new Empty(x, y);
            }
        }

        Random rand = new Random();

        for (int nCell = 0; nCell < 100; nCell++) {

            int x = rand.nextInt(20);
            int y = rand.nextInt(20);
            if (map[x][y] instanceof Empty) {
                if (nCell < 33) {
                    map[x][y] = new XCell(x, y);
                } else if (nCell < 66) {
                    map[x][y] = new YCell(x, y);
                } else {
                    map[x][y] = new ZCell(x, y);
                }

            } else {
                nCell--;
            }
        }
    }


}
