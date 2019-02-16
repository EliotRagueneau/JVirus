package Controllers;

import Contents.Cells.Cell;
import Contents.Cells.ImmunizedCells.XCell;
import Contents.Cells.SensibleCells.YCell;
import Contents.Cells.SensibleCells.ZCell;
import Contents.Content;
import Contents.LocatedContent;
import Contents.Virus.Virus;
import Contents.Virus.VirusA;
import Enums.Direction;
import Utils.IO;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
    private static Map map;
    private int turnsByPLayer = 4;
    private int mapSize = 20;
    private float xProp = 0.2f;
    private float yProp = 0.4f;
    private float zProp = 0.4f;


    public Game() {
        menu();
        List<Content> toSpawn = new ArrayList<>();
        int nbCell = (mapSize * mapSize) / 4;
        toSpawn.addAll(new XCell().getNInstances(Math.round(nbCell * xProp)));
        toSpawn.addAll(new YCell().getNInstances(Math.round(nbCell * yProp)));
        toSpawn.addAll(new ZCell().getNInstances(Math.round(nbCell * zProp)));
        toSpawn.addAll(new VirusA().getNInstances(nbCell / 10));

        map = new Map(mapSize, toSpawn);

        IO.print("Vous voulez jouer contre :\n");
        IO.print("\t1. Un autre joueur\n");
        IO.print("\t2. L'ordinateur\n");
        boolean vsPlayer = true;
        if (IO.intInput() == 2) {
            vsPlayer = false;
        }
        Random random = new Random();

        while (noVictory()) {
            map.show();
            if (vsPlayer) {
                for (int i = 0; i < turnsByPLayer; i++) {
                    IO.print("Cellules, tour : " + (i + 1) + "\n");
                    //On indique à selectLocatedContent la classe à sélectionner
                    LocatedContent selectedLocatedContent = map.selectLocatedContent(Cell.class);
                    selectedLocatedContent.menu();
                    map.show();
                }
            } else {
                List<LocatedContent> cells = map.getLocatedHealthyCells();
                for (int i = 0; i < turnsByPLayer; i++) {
                    LocatedContent rdmLocatedContent = cells.remove(random.nextInt(cells.size()));
                    map.move(rdmLocatedContent, Direction.randomDirection(rdmLocatedContent));
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    map.show();
                }
            }
            for (int i = 0; i < turnsByPLayer; i++) {
                IO.print("Virus, tour : " + (i + 1) + "\n");
                LocatedContent selectedLocatedContent = map.selectLocatedContent(Virus.class);
                selectedLocatedContent.menu();
                map.show();
            }
            map.turn();
        }
    }

    public static Map getMap() {
        return map;
    }

    private void menu() {
        IO.print("Bienvenue dans JVirus !\n");
        IO.print("\t1. Jouer\n");
        IO.print("\t2. Options\n");
        if (IO.intInput() == 2) {
            configure();
        }
    }

    private void configure() {
        while (true) {
            IO.clearConsole();
            IO.print("=====OPTIONS=====\n");
            IO.print("\t1. Modifier la taille du plateau\n");
            IO.print("\t2. Modifier la difficulté\n");
            IO.print("\t3. Exit\n");
            switch (IO.intInput()) {
                case 1:
                    mapSize = IO.intInput("Quelle taille de plateau désirez vous (Entre 1 et 26 de préférence)?\n");
                    turnsByPLayer = mapSize * mapSize / 80;
                    break;
                case 2:
                    IO.print("Quelle difficulté désirez vous ?\n");
                    IO.print("\t1. Facile\n");
                    IO.print("\t2. Normale\n");
                    IO.print("\t3. Difficile\n");
                    switch (IO.intInput()) {
                        case 1:
                            xProp = 0.1f;
                            yProp = 0.7f;
                            zProp = 0.2f;
                            break;
                        case 2:
                            xProp = 0.2f;
                            yProp = 0.4f;
                            zProp = 0.4f;
                            break;
                        case 3:
                            xProp = 0.4f;
                            yProp = 0.2f;
                            zProp = 0.4f;
                            break;
                        default:
                            IO.print("Vous n'avez pas changé la difficulté.");
                    }
                    break;
                case 3:
                    return;
            }

        }
    }

    private boolean noVictory() {
        if (map.getNbVirus() < turnsByPLayer) {
            IO.print("Félicitations aux cellules, elles ont gagné ... pour le moment ...");
            System.exit(0);
        }
        if (map.getNbSensibleCells() == 0) {
            IO.print("Félicitations aux virus, ils ont détruit leur hôte et vont mourir ...");
            System.exit(0);
        }
        return true;
    }
}
