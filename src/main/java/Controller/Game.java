package Controller;

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
    private final int turnsByPLayer = 4;
    private boolean vsPlayer = true;

    public Game() {
        final int mapSize = 20;

        List<Content> toSpawn = new ArrayList<>();
        toSpawn.addAll(new XCell().getNInstances((mapSize * mapSize) / 16));
        toSpawn.addAll(new YCell().getNInstances((mapSize * mapSize) / 16));
        toSpawn.addAll(new ZCell().getNInstances((mapSize * mapSize) / 16));
        toSpawn.addAll(new VirusA().getNInstances((mapSize * mapSize) / 40));

        map = new Map(mapSize, toSpawn);
        menu();
    }

    public static Map getMap() {
        return map;
    }

    public void menu() {
        IO.print("Bienvenue dans JVirus !\n");
        IO.print("Vous voulez jouer contre :\n");
        IO.print("\t1. Un autre joueur\n");
        IO.print("\t2. L'ordinateur\n");
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
