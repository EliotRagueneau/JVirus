import Utils.IO;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Map {
    public int MAP_SIZE;

    private Content[][] map;

    public Map(int MAP_SIZE) {
        this.MAP_SIZE = MAP_SIZE;
        map = new Content[MAP_SIZE][MAP_SIZE];

        List<Content> toAdd = new ArrayList<>();

        toAdd.addAll(XCell.getNInstances(30));

        List<int[]> empties = new ArrayList<>();

        for (int x = 0; x < MAP_SIZE; x++) {
            for (int y = 0; y < MAP_SIZE; y++) {
                map[y][x] = new Content(x, y);
                empties.add(new int[]{x, y});
            }
        }

        Random rand = new Random();

        for (int n = 0; n < 110; n++) {

            int[] coords = empties.remove(rand.nextInt(empties.size()));
            int x = coords[0];
            int y = coords[1];

            if (n < 33) {
                map[y][x] = new XCell(x, y);
            } else if (n < 66) {
                map[y][x] = new YCell(x, y);
            } else if (n < 100) {
                map[y][x] = new ZCell(x, y);
            } else {
                map[y][x] = new VirusA(x, y);
            }
//            show();
//            try {
//                Thread.sleep(10);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.print("\033[H\033[2J");
//            System.out.flush();
        }

        show();

        Content a = new YCell(0, 0);
        Content b = new VirusA(0, 0);
        ((VirusA) b).turn();
        Content c = a.fuse(b);
        ((InfectedCell) c).turn();
        ((InfectedCell) c).turn();
        ((InfectedCell) c).turn();
        c.show();

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

    public Content selectContent(int x, int y) {
        return map[y][x];
    }


    public void replaceContent(int x, int y, Content replace){
        map[y][x] = replace;
    }

    public Content selectContent() {
        String input = IO.input("Quelle case voulez vous choisir ? (Ex : A 1)\n");
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
            IO.print("Mauvaise entrée");
            return selectContent();
        }
    }

    public Content selectCell(){ //Même fonction qu'au dessus mais juste avec les cellules uniquement
        String input = IO.input("Quelle case voulez vous choisir ? (Ex : A 1)\n");
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
            Content content = selectContent(x, y);
            if (content instanceof Cell){
                return content;
            } else {
                IO.print("Sélectionnez une cellule, pas un virus !");
                return selectContent();
            }
        } else {
            IO.print("Mauvaise entrée");
            return selectContent();
        }
    }

    public Content selectVirus() { //Même fonction qu'au dessus mais juste avec les cellules uniquement
        String input = IO.input("Quelle case voulez vous choisir ? (Ex : A 1)\n");
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
            Content content = selectContent(x, y);
            if (content instanceof Virus) {
                return content;
            } else {
                IO.print("Sélectionnez un virus, pas une cellule !");
                return selectContent();
            }
        } else {
            IO.print("Mauvaise entrée");
            return selectContent();
        }
    }

    public void exchangePosition(Content a, Content b) {
        map[a.getY()][a.getX()] = b;
        map[b.getY()][b.getX()] = a;
    }
}
