import Content.*;
import Content.Enums.Direction;
import Content.Enums.TurnOver;
import Utils.IO;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Map implements Timed {
    public final int MAP_SIZE;

    private final Content[][] map;

    public Map(int MAP_SIZE, List<Content> toSpawn) {
        this.MAP_SIZE = MAP_SIZE;
        map = new Content[MAP_SIZE][MAP_SIZE];

        List<int[]> empties = new ArrayList<>();

        for (int x = 0; x < MAP_SIZE; x++) {
            for (int y = 0; y < MAP_SIZE; y++) {
                map[y][x] = new Content();
                empties.add(new int[]{x, y});
            }
        }

        Random rand = new Random();

        while (!toSpawn.isEmpty()) {
            Content content = toSpawn.remove(toSpawn.size() - 1);
            int[] coords = empties.remove(rand.nextInt(empties.size()));
            int x = coords[0];
            int y = coords[1];

            map[y][x] = content;
        }

//        show();
//
//        Content.Content a = new Content.Cell.SensibleCell.YCell();
//        Content.Content b = new Content.Virus.VirusA();
//        ((Content.Virus.VirusA) b).turn();
//        Content.Content c = a.fuse(b);
//        ((Content.Cell.InfectedCell) c).turn();
//        ((Content.Cell.InfectedCell) c).turn();
//        ((Content.Cell.InfectedCell) c).turn();
//        c.show();

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

//        Print Border + Content.Content
        System.out.print(headline.toString());
        System.out.print(line.toString());

        for (int y = 0; y < MAP_SIZE; y++) {

            System.out.print(String.format("%2d", y + 1) + " │");

            for (int x = 0; x < MAP_SIZE; x++) {
                map[y][x].show();
            }
            System.out.print("│ " + String.format("%-2d", y + 1) + '\n');
        }
        int length = line.length();

        System.out.print(line.replace(3, 4, "└")
                .replace(length - 2, length - 1, "┘")
                .toString());
        System.out.print(headline);
    }

    public Case selectCase(Class toChoose) {
        String input = IO.input("Quelle case voulez vous choisir ? (Ex : A 1)\n");
        int x;
        int y;
        if (input.matches(String.format("[a-%cA-%c][ \\-]?\\d{1,2}", 'a' + MAP_SIZE, 'A' + MAP_SIZE))) {
            String[] arr = input.split("[ -]");
            char col = arr[0].toUpperCase().charAt(0);
            x = col - 'A';
            if (arr.length == 2) {
                y = Integer.valueOf(arr[1]) - 1;
            } else {
                y = Integer.valueOf(arr[0].substring(1)) - 1;
                if (y > MAP_SIZE) {
                    IO.print("Ligne trop élevée ( doit être compris entre 1 et " + MAP_SIZE + ")\n");
                    return selectCase(toChoose);
                }
            }

        } else if (input.matches(String.format("\\d{1,2}[ \\-]?[a-%cA-%c]", 'a' + MAP_SIZE, 'A' + MAP_SIZE))) {
            String[] arr = input.split("[ -]");
            char col = arr[arr.length - 1].toUpperCase().charAt(0);
            // TODO Inverse du précédent
            x = 0;
            y = 0;

        } else {
            IO.print("Mauvaise entrée\n");
            return selectCase(toChoose);
        }

        Content content = map[y][x];
        if (toChoose.isInstance(content)) {
            if (content.isMovable()) {
                return new Case(x, y, content);
            } else {
                IO.print("Vous avez déjà déplacé cet élément, veuillez en choisir un autre.\n");
                return selectCase(toChoose);
            }

        } else {
            try { //On récupère la méthode wrongSelect de l'objet choisi par ses coordonnées qui indique selon si c'est un virus ou une cellule qu'il faut sélectionner le bon objet (c'était pour le fun, mais ça rend bien !)
                toChoose.getMethod("wrongSelect", null).invoke(null);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
            return selectCase(toChoose);
        }
    }


    public void move(Case selCase, Direction direction) {
        try {
            switch (direction) {
                case UP:
                    map[selCase.y - 1][selCase.x] = selCase.content.fuse(map[selCase.y - 1][selCase.x]);
                    break;
                case DOWN:
                    map[selCase.y + 1][selCase.x] = selCase.content.fuse(map[selCase.y + 1][selCase.x]);
                    break;
                case LEFT:
                    map[selCase.y][selCase.x - 1] = selCase.content.fuse(map[selCase.y][selCase.x - 1]);
                    break;
                case RIGHT:
                    map[selCase.y][selCase.x + 1] = selCase.content.fuse(map[selCase.y][selCase.x + 1]);
                    break;
            }
            map[selCase.y][selCase.x] = new Content();
        } catch (ArrayIndexOutOfBoundsException e) {
            IO.print("Vous allez sortir de la map...\n");
            selCase.menu();
        }
        selCase.content.setMovable(false);
        show();
    }

    @Override
    public TurnOver turn() {
        for (int x = 0; x < MAP_SIZE; x++) {
            for (int y = 0; y < MAP_SIZE; y++) {
                Content content = map[y][x];
                content.setMovable(true);
                if (content instanceof Timed) {
                    TurnOver turnOver = ((Timed) content).turn();
                    switch (turnOver) {
                        case EXPLODE:
                            // TODO explode
                            break;
                        case DIE:
                            map[y][x] = new Content();
                            break;
                        default:
                            break;
                    }
                }
            }
        }
        return TurnOver.NOTHING;
    }
}
