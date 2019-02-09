package Controller;

import Content.Case;
import Content.Cell.Cell;
import Content.Cell.ImmunizedCell.XCell;
import Content.Cell.SensibleCell.YCell;
import Content.Cell.SensibleCell.ZCell;
import Content.Content;
import Content.Enums.Direction;
import Content.Virus.Virus;
import Content.Virus.VirusA;
import Utils.IO;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
    private static Map map;
    private static boolean vsPlayer = false;

    public static void init() {
        List<Content> toSpawn = new ArrayList<>();

        toSpawn.addAll(new XCell().getNInstances(30));
        toSpawn.addAll(new YCell().getNInstances(30));
        toSpawn.addAll(new ZCell().getNInstances(30));
        toSpawn.addAll(new VirusA().getNInstances(10));

        map = new Map(20, toSpawn);
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
                for (int i = 0; i < 4; i++) {
                    IO.print("Cellules, tour : " + (i + 1) + "\n");
                    Case selectedCase = map.selectCase(Cell.class); //Selection uniquement des cellules
                    selectedCase.menu(); //Appel du menu de la cellule sélectionnée
                    map.show();
                }
            } else {
                List<Case> cells = map.getCellCases();
                for (int i = 0; i < 4; i++) {
                    Case cellCase = cells.remove(random.nextInt(cells.size()));
                    map.move(cellCase, Direction.randomDirection(cellCase));
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    map.show();
                }
            }

            for (int i = 0; i < 4; i++) {
                IO.print("Virus, tour : " + (i + 1) + "\n");
                Case selectedCase = map.selectCase(Virus.class); //Selection uniquement des cellules
                selectedCase.menu(); //Appel du menu de la cellule sélectionnée
                map.show();
            }
            map.turn();
        }

    }

    public static Map getMap() {
        return map;
    }
}
