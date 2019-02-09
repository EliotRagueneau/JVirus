package Content.Cell.SensibleCell;

import Content.Cell.Cell;
import Content.Cell.InfectedCell;
import Content.Virus.Virus;

import java.lang.reflect.InvocationTargetException;

public abstract class SensibleCell extends Cell {
    public SensibleCell(int immunityLevel) {
        super(immunityLevel);
    }

    public InfectedCell fuse(Virus virus) {
        try {
            return new InfectedCell(immunityLevel, virus.getClass().getConstructor().newInstance());
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        }
    }

//    @Override
//    public void menu(Content.Case selectedCase) {
//        super.menu(selectedCase);
//        IO.print("Que voulez-vous faire avec cette cellule ?\n");
//        IO.print("8 : Vous déplacer vers le haut\n");
//        IO.print("2 : Vous déplacer vers le bas\n");
//        IO.print("4 : Vous déplacer vers la gauche\n");
//        IO.print("6 : Vous déplacer vers la droite\n");
////        IO.print("0 : Commettre l'irréparable... l'apoptose !\n");
//        int rep = IO.intInput();
//        Controller.Map map = Controller.Game.getMap();
//        switch (rep) {
//            case 8:
//                map.move(selectedCase, Content.Enums.Direction.UP);
//                break;
//            case 2:
//                map.move(selectedCase, Content.Enums.Direction.DOWN);
//                break;
//            case 4:
//                map.move(selectedCase, Content.Enums.Direction.LEFT);
//                break;
//            case 6:
//                map.move(selectedCase, Content.Enums.Direction.RIGHT);
//                break;
//        }
//    }
}
