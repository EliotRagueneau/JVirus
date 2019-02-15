package Controller;

import Contents.Cells.Cell;
import Contents.Cells.InfectedCell;
import Contents.Cells.SensibleCells.SensibleCell;
import Contents.Content;
import Contents.Enums.Direction;
import Contents.Enums.TurnCallBack;
import Contents.Interfaces.Timed;
import Contents.LocatedContent;
import Contents.Virus.Virus;
import Utils.IO;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Two dimensional container of contents that ensure their representation for the user
 * and store positions of contents.
 */
public class Map implements Timed {
    public final int MAP_SIZE;

    private final static Random RANDOM = new Random();

    private final Content[][] map;

    private final List<LocatedContent> locatedHealthyCells = new LinkedList<>();

    private int nbVirus = 0;

    private int nbSensibleCells = 0;

    /**
     * @param MAP_SIZE Size of the square map to generate
     * @param toSpawn  List of contents to place randomly on the newly generated map
     */
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
                if (!(content instanceof InfectedCell)) {
                    locatedHealthyCells.add(new LocatedContent(x, y, content));
                    if (content instanceof SensibleCell) {
                        nbSensibleCells++;
                    }
                }
            } else if (content instanceof Virus) {
                nbVirus++;
            }
        }
    }

    public List<LocatedContent> getLocatedHealthyCells() {
        return locatedHealthyCells;
    }

    public int getNbVirus() {
        return nbVirus;
    }

    public int getNbSensibleCells() {
        return nbSensibleCells;
    }

    /**
     * @param toChoose Class of content that has to be selected
     * @return Content located by user
     */
    public LocatedContent selectLocatedContent(Class<? extends Content> toChoose) {
        String input = IO.input("Quelle case voulez vous choisir ? (Ex : A 1)\n");
        int x, y;
        if (input.matches(String.format("[a-%cA-%c][ \\-]?\\d{1,2}", 'a' + MAP_SIZE, 'A' + MAP_SIZE))) {
            String[] arr = input.split("[ -]");
            char col = arr[0].toUpperCase().charAt(0);
            x = col - 'A';  // Convert a character to an int by its place on UTF8 table relative to the char 'A'
            if (arr.length == 2) {
                y = Integer.valueOf(arr[1]) - 1;
            } else {
                y = Integer.valueOf(arr[0].substring(1)) - 1;
                if (y > MAP_SIZE) {
                    IO.print("Ligne trop élevée (doit être compris entre 1 et " + MAP_SIZE + ")\n");
                    return selectLocatedContent(toChoose);
                }
            }

        } else if (input.matches(String.format("\\d{1,2}[ \\-]?[a-%cA-%c]", 'a' + MAP_SIZE, 'A' + MAP_SIZE))) {
            char col = input.toUpperCase().charAt(input.length() - 1);
            x = col - 'A';  // Convert a character to an int by its place on UTF8 table relative to the char 'A'
            input = input.substring(0, input.length() - 1);
            y = Integer.valueOf(input.split("[ -]")[0]) - 1;
            if (y > MAP_SIZE) {
                IO.print("Ligne trop élevée (doit être compris entre 1 et " + MAP_SIZE + ")\n");
                return selectLocatedContent(toChoose);
            }

        } else {
            IO.print("Mauvaise entrée\n");
            return selectLocatedContent(toChoose);
        }

        Content content = map[y][x];

        // Check if content is an instance of the desired class given as method parameter
        if (!toChoose.isInstance(content)) {
            if (toChoose == Cell.class) {
                IO.print("Veuillez sélectionner une cellule.");
            } else {
                IO.print("Veuillez sélectionner un virus.");
            }
            return selectLocatedContent(toChoose);
        }

        if (!content.isMovable()) {
            IO.print("Vous avez déjà déplacé cet élément, veuillez en choisir un autre.\n");
            return selectLocatedContent(toChoose);
        }

        return new LocatedContent(x, y, content);
    }

    /**
     * Move a located content toward a direction on the map
     *
     * @param what      Content that has to be moved on the map
     * @param direction Direction in which the content has to move
     */
    public void move(LocatedContent what, Direction direction) {
        try {
            switch (direction) {
                case UP:
                    map[what.y - 1][what.x] = what.content.fuse(map[what.y - 1][what.x]);
                    break;
                case DOWN:
                    map[what.y + 1][what.x] = what.content.fuse(map[what.y + 1][what.x]);
                    break;
                case LEFT:
                    map[what.y][what.x - 1] = what.content.fuse(map[what.y][what.x - 1]);
                    break;
                case RIGHT:
                    map[what.y][what.x + 1] = what.content.fuse(map[what.y][what.x + 1]);
                    break;
            }
            map[what.y][what.x] = new Content();
            what.content.setMovable(false);
        } catch (ArrayIndexOutOfBoundsException e) {
            IO.print("Vous allez sortir de la map...\n");
            what.menu();
        }
    }

    /**
     * Updates lists of contents that can be used, such as locatedHealthyCells.
     * Call the turn methods of map's contents and treat their callbacks
     *
     * @return Nothing
     */
    @Override
    public TurnCallBack turn() {
        locatedHealthyCells.clear();
        nbVirus = 0;
        nbSensibleCells = 0;
        for (int x = 0; x < MAP_SIZE; x++) {
            for (int y = 0; y < MAP_SIZE; y++) {
                Content content = map[y][x];
                content.setMovable(true);
                if (content instanceof Cell) {
                    if (!(content instanceof InfectedCell)) {
                        locatedHealthyCells.add(new LocatedContent(x, y, content));
                        if (content instanceof SensibleCell) {
                            nbSensibleCells++;
                        }
                    }
                } else if (content instanceof Virus) {
                    nbVirus++;
                }

                if (content instanceof Timed) {
                    TurnCallBack turnCallBack = ((Timed) content).turn();
                    switch (turnCallBack) {
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
        return TurnCallBack.NOTHING;
    }

    /**
     * Remove an infected cell from the map and spreads its inners virions
     *
     * @param c Located Content containing an InfectedCell
     */
    public void explode(LocatedContent c) {
        map[c.y][c.x] = new Content();
        List<Virus> toSpread = ((InfectedCell) c.content).getVirions();
        for (Virus virion : toSpread) {
            randomPlacing(c.x, c.y, virion);
        }
    }

    /**
     * Recursive function that place a virion around its first position if the previous one was occupied by a virus
     *
     * @param x      Previous x position on the map
     * @param y      Previous y position on the map
     * @param virion Newly born virus that has to be placed
     */
    private void randomPlacing(int x, int y, Virus virion) {
        if (map[y][x] instanceof Virus) {
            x += (1 - RANDOM.nextInt(3));
            y += (1 - RANDOM.nextInt(3));
            randomPlacing(x, y, virion);
        } else {
            map[y][x] = virion.fuse(map[y][x]);
        }
    }

    /**
     * Print map for users
     */
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

//        Print Border + Contents.Contents
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
}