package Controller;

import Content.Cell.Cell;
import Content.Cell.InfectedCell;
import Content.Content;
import Content.Enums.Direction;
import Content.Enums.TurnOver;
import Content.Timed;
import Content.Virus.Virus;
import Content.LocatedContent;
import Utils.IO;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

public class Map implements Timed {
    public final int MAP_SIZE;

    private final static Random RANDOM = new Random();

    private final Content[][] map;

    private final List<LocatedContent> cells = new LinkedList<>();

    public List<LocatedContent> getCellCases() {
        return cells;
    }

    public Map(int MAP_SIZE, List<Content> toSpawn) {
        this.MAP_SIZE = MAP_SIZE;
        map = new Content[MAP_SIZE][MAP_SIZE];

        List<int[]> empties = new LinkedList<>();

        for (int x = 0; x < MAP_SIZE; x++) {
            for (int y = 0; y < MAP_SIZE; y++) {
                map[y][x] = new Content();
                empties.add(new int[]{x, y});
            }
        }


        while (!toSpawn.isEmpty()) {
            Content content = toSpawn.remove(toSpawn.size() - 1);
            int[] coords = empties.remove(RANDOM.nextInt(empties.size()));
            int x = coords[0];
            int y = coords[1];

            map[y][x] = content;
            if (content instanceof Cell) {
                cells.add(new LocatedContent(x, y, content));
            }
        }
    }

    public void show() {
        IO.clearConsole();
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

    public LocatedContent selectCase(Class<? extends Content> toChoose) {
        String input = IO.input("Quelle case voulez vous choisir ? (Ex : A 1)\n(Passer son tour : 0)\n");
        int x;
        int y;
//        if ("0".equals(input)){}
        if (input.matches(String.format("[a-%cA-%c][ \\-]?\\d{1,2}", 'a' + MAP_SIZE, 'A' + MAP_SIZE))) {
            String[] arr = input.split("[ -]");
            char col = arr[0].toUpperCase().charAt(0);
            x = col - 'A';
            if (arr.length == 2) {
                y = Integer.valueOf(arr[1]) - 1;
            } else {
                y = Integer.valueOf(arr[0].substring(1)) - 1;
                if (y > MAP_SIZE) {
                    IO.print("Ligne trop élevée (doit être compris entre 1 et " + MAP_SIZE + ")\n");
                    return selectCase(toChoose);
                }
            }

        } else if (input.matches(String.format("\\d{1,2}[ \\-]?[a-%cA-%c]", 'a' + MAP_SIZE, 'A' + MAP_SIZE))) {
            char col = input.toUpperCase().charAt(input.length() - 1);
            x = col - 'A';
            input = input.substring(0, input.length() - 1);
            y = Integer.valueOf(input.split("[ -]")[0]) - 1;
            if (y > MAP_SIZE) {
                IO.print("Ligne trop élevée (doit être compris entre 1 et " + MAP_SIZE + ")\n");
                return selectCase(toChoose);
            }

        } else {
            IO.print("Mauvaise entrée\n");
            return selectCase(toChoose);
        }

        Content content = map[y][x];
        if (toChoose.isInstance(content)) {
            if (content.isMovable()) {
                return new LocatedContent(x, y, content);
            } else {
                IO.print("Vous avez déjà déplacé cet élément, veuillez en choisir un autre.\n");
                return selectCase(toChoose);
            }

        } else {
//            try { //On récupère la méthode wrongSelect de l'objet choisi par ses coordonnées qui indique selon si c'est un virus ou une cellule qu'il faut sélectionner le bon objet (c'était pour le fun, mais ça rend bien !)
//                toChoose.getMethod("wrongSelect", null).invoke(null);
//            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
//                e.printStackTrace();
//            }
            if (toChoose == Cell.class) {
                Cell.wrongSelect();
            } else {
                Virus.wrongSelect();
            }
            return selectCase(toChoose);
        }
    }


    public void move(LocatedContent selLocatedContent, Direction direction) {
        try {
            switch (direction) {
                case UP:
                    map[selLocatedContent.y - 1][selLocatedContent.x] = selLocatedContent.content.fuse(map[selLocatedContent.y - 1][selLocatedContent.x]);
                    break;
                case DOWN:
                    map[selLocatedContent.y + 1][selLocatedContent.x] = selLocatedContent.content.fuse(map[selLocatedContent.y + 1][selLocatedContent.x]);
                    break;
                case LEFT:
                    map[selLocatedContent.y][selLocatedContent.x - 1] = selLocatedContent.content.fuse(map[selLocatedContent.y][selLocatedContent.x - 1]);
                    break;
                case RIGHT:
                    map[selLocatedContent.y][selLocatedContent.x + 1] = selLocatedContent.content.fuse(map[selLocatedContent.y][selLocatedContent.x + 1]);
                    break;
            }
            map[selLocatedContent.y][selLocatedContent.x] = new Content();
        } catch (ArrayIndexOutOfBoundsException e) {
            IO.print("Vous allez sortir de la map...\n");
            selLocatedContent.menu();
        }
        selLocatedContent.content.setMovable(false);
    }

    @Override
    public TurnOver turn() {
        cells.clear();
        for (int x = 0; x < MAP_SIZE; x++) {
            for (int y = 0; y < MAP_SIZE; y++) {
                Content content = map[y][x];
                content.setMovable(true);
                if (content instanceof Cell) {
                    cells.add(new LocatedContent(x, y, content));
                }
                if (content instanceof Timed) {
                    TurnOver turnOver = ((Timed) content).turn();
                    switch (turnOver) {
                        case EXPLODE:
                            explode(new LocatedContent(x, y, content));
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

    private void randomPlacing(int x, int y, Virus virion) {
        if (map[y][x] instanceof Virus) {
            x += (1 - RANDOM.nextInt(3));
            y += (1 - RANDOM.nextInt(3));
            randomPlacing(x, y, virion);
        } else {
            map[y][x] = virion.fuse(map[y][x]);
        }
    }

    public void explode(LocatedContent c) {
        int a = c.x;
        int b = c.y;
        Random r = new Random();
        map[c.y][c.x] = new Content();
        Vector<Virus> toSpread = ((InfectedCell) c.content).getVirions();
        for (Virus virion : toSpread) {
            randomPlacing(c.x, c.y, virion);
        }
    }

}
