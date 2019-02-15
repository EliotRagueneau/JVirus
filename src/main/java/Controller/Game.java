package Controller;

import Content.Cell.Cell;
import Content.Cell.ImmunizedCell.XCell;
import Content.Cell.SensibleCell.YCell;
import Content.Cell.SensibleCell.ZCell;
import Content.Content;
import Content.Enums.Direction;
import Content.LocatedContent;
import Content.Virus.Virus;
import Content.Virus.VirusA;
import Utils.IO;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
    private static Map map;
    private static boolean vsPlayer = true;

    public static void init() {
        List<Content> toSpawn = new ArrayList<>();
        final int mapSize = 20;
        toSpawn.addAll(new XCell().getNInstances((mapSize * mapSize) / 16));
        toSpawn.addAll(new YCell().getNInstances((mapSize * mapSize) / 16));
        toSpawn.addAll(new ZCell().getNInstances((mapSize * mapSize) / 16));
        toSpawn.addAll(new VirusA().getNInstances((mapSize * mapSize) / 40));

        map = new Map(mapSize, toSpawn);
        menu();
    }

    public static void menu() {
        IO.print("Bienvenue dans JVirus !\n");
        IO.print("Vous voulez jouer contre :\n");
        IO.print("\t1. Un autre joueur\n");
        IO.print("\t2. L'ordinateur\n");
        if (IO.intInput() == 2) {
            vsPlayer = false;
        }
        Random random = new Random();

        while (true) {
            map.show();
            if (vsPlayer) {
                for (int i = 0; i < 1; i++) {
                    IO.print("Cellules, tour : " + (i + 1) + "\n");
                    LocatedContent selectedLocatedContent = map.selectCase(Cell.class); //Appel de la méthode en spécifiant qu'on veut une classe Cell
                    selectedLocatedContent.menu(); //Appel du menu
                    map.show();
                }
            } else {
                List<LocatedContent> cells = map.getCellCases();
                for (int i = 0; i < 1; i++) {
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

            for (int i = 0; i < 1; i++) {
                IO.print("Virus, tour : " + (i + 1) + "\n");
                LocatedContent selectedLocatedContent = map.selectCase(Virus.class); //Selection uniquement des cellules
                selectedLocatedContent.menu(); //Appel du menu de la cellule sélectionnée
                map.show();
            }
            map.turn();
        }

    }

    public static Map getMap() {
        return map;
    }
}
