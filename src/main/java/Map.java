import java.util.Random;

public class Map {
    private static Content[][] map = new Content[20][20];

    public Map() {
        for (int x = 0; x < 20; x++) {
            for (int y = 0; y < 20; y++) {
                map[y][x] = new Empty(x, y);
            }
        }
        Random rand = new Random();


        for (int n = 0; n < 110; n++) {

            int x = rand.nextInt(20);
            int y = rand.nextInt(20);
            if (map[x][y] instanceof Empty) {
                if (n < 33) {
                    map[y][x] = new XCell(x, y);
                } else if (n < 66) {
                    map[y][x] = new YCell(x, y);
                } else if (n < 100) {
                    map[y][x] = new ZCell(x, y);
                } else {
                    map[y][x] = new Virus(x, y);
                }

            } else {
                n--;
            }
        }
    }

    public void show() {
        System.out.print("     A  B  C  D  E  F  G  H  I  J  K  L  M  N  O  P  Q  R  S  T\n");
        System.out.print("   ┌────────────────────────────────────────────────────────────┐\n");

        for (Content[] line : map) {
            System.out.print(String.format("%2d", line[0].getY() + 1) + " │");
            for (Content content : line) {
                content.show();
            }
            System.out.print("│ " + String.format("%-2d", line[0].getY() + 1) + '\n');
        }
        System.out.print("   └────────────────────────────────────────────────────────────┘\n");
        System.out.print("     A  B  C  D  E  F  G  H  I  J  K  L  M  N  O  P  Q  R  S  T\n");
    }
}
