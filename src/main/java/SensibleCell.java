import Utils.IO;

import java.lang.reflect.InvocationTargetException;

public abstract class SensibleCell extends Cell {
    public SensibleCell(int x, int y, int immunityLevel) {
        super(x, y, immunityLevel);
    }

    public InfectedCell fuse(Virus virus) {
        try {
            return new InfectedCell(x, y, immunityLevel, virus.getClass().getConstructor(new Class[]{int.class, int.class}).newInstance(x, y));
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void menu() {
        super.menu();
        IO.print("Que voulez-vous faire avec cette cellule immunisée ?\n");
        IO.print("8 : Vous déplacer vers le haut\n");
        IO.print("2 : Vous déplacer vers le bas\n");
        IO.print("4 : Vous déplacer vers la gauche\n");
        IO.print("6 : Vous déplacer vers la droite\n");
//        IO.print("0 : Commettre l'irréparable... l'apoptose !\n");
        int rep = IO.intInput();
        switch (rep){
            case 8:
                move(Direction.UP);
            case 2:
                move(Direction.DOWN);
            case 4:
                move(Direction.LEFT);
            case 6:
                move(Direction.RIGHT);
//            case 0:
//                apoptose();
        }
    }
}
