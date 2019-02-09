import Content.Cell.Cell;
import Content.Cell.ImmunizedCell.XCell;
import Content.Cell.SensibleCell.YCell;
import Content.Cell.SensibleCell.ZCell;
import Content.Content;
import Content.Virus.Virus;
import Content.Virus.VirusA;
import Utils.IO;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private static Map map;

    public static void main(String[] args) {
        List<Content> toSpawn = new ArrayList<>();

        toSpawn.addAll(new XCell().getNInstances(30));
        toSpawn.addAll(new YCell().getNInstances(30));
        toSpawn.addAll(new ZCell().getNInstances(30));
        toSpawn.addAll(new VirusA().getNInstances(10));

        map = new Map(20, toSpawn);
        menu();
    }

    public static void menu(){
        while (true) {
            map.show();
            for (int i = 0; i < 1; i++) {
                IO.print("Cellules, tour : " + (i + 1) + "\n");
                Case selectedCase = map.selectCase(Cell.class); //Selection uniquement des cellules
                selectedCase.menu(); //Appel du menu de la cellule sélectionnée
                map.show();
            }
            for (int i = 0; i < 1; i++) {
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
