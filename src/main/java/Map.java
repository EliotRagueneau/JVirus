import Utils.IO;

import java.util.Random;

public class Map {
    public static final int MAP_SIZE = 20;
    public static final int NB_CASE = MAP_SIZE * MAP_SIZE;

    private static Content[] map = new Content[NB_CASE];

    public Map() {
        for (int x = 0; x < NB_CASE; x++) {
            map[x] = new Empty(x / MAP_SIZE, x % MAP_SIZE);

        }
        Random rand = new Random();


        for (int n = 0; n < 110; n++) {

            int x = rand.nextInt(NB_CASE);

            if (map[x] instanceof Empty) {
                if (n < 33) {
                    map[x] = new XCell(x / MAP_SIZE, x % MAP_SIZE);
                } else if (n < 66) {
                    map[x] = new YCell(x / MAP_SIZE, x % MAP_SIZE);
                } else if (n < 100) {
                    map[x] = new ZCell(x / MAP_SIZE, x % MAP_SIZE);
                } else {
                    map[x] = new Virus(x / MAP_SIZE, x % MAP_SIZE);
                }

            } else {
                n--;
            }
        }

        show();
        Content a = selectContent();
    }

    public void show() {
//        Generate Border

        StringBuilder headline = new StringBuilder("     ");
        StringBuilder line = new StringBuilder("   ┌");

        for (int x = 'A'; x < 'A' + MAP_SIZE; x++) {
            headline.append((char) x);
            headline.append("  ");
            line.append("───");
        }

        headline.append('\n');
        line.append("┐\n");

//        Print Border + Content

        System.out.print(headline.toString());
        System.out.print(line.toString());

        for (int y = 0; y < MAP_SIZE; y++) {

            System.out.print(String.format("%2d", y + 1) + " │");

            for (int x = 0; x < MAP_SIZE; x++) {
                selectContent(x, y).show();
            }

            System.out.print("│ " + String.format("%-2d", y + 1) + '\n');
        }

        int length = line.length();

        System.out.print(line.replace(3, 4, "└")
                .replace(length - 2, length - 1, "┘")
                .toString());

        System.out.print(headline);
    }

    private Content selectContent(int x, int y) {
        return map[y * MAP_SIZE + x];
    }

    public Content selectContent() {


        String input = IO.input("Quelle case voulez vous choisir ? (A 1)\n");
        if (input.matches(String.format("[a-%cA-%c][ \\-]?\\d{1,2}", (char) ('a' + MAP_SIZE), (char) ('A' + MAP_SIZE)))) {
            String[] arr = input.split("[ -]");
            char col = arr[0].toUpperCase().charAt(0);
            int x = col - 'A';
            int y;
            if (arr.length == 2) {
                y = Integer.valueOf(arr[1]) - 1;
            } else {
                y = Integer.valueOf(arr[0].substring(1)) - 1;
                if (y > MAP_SIZE) {
                    IO.print("Ligne trop élevée ( doit être compris entre 1 et " + MAP_SIZE);
                    return selectContent();
                }
            }
            return selectContent(x, y);
        } else {
            IO.print("Mauvais entrée");
            return selectContent();
        }
    }
}
