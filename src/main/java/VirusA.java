import Utils.IO;

public class VirusA extends Virus {
    public VirusA(int x, int y) {
        super(x, y, 10, 1, 0);
    }

    @Override
    public void menu() {
        super.menu();
        IO.print("Que voulez-vous faire avec cette cellule immunisée ?\n");
        IO.print("8 : Vous déplacer vers le haut\n");
        IO.print("2 : Vous déplacer vers le bas\n");
        IO.print("4 : Vous déplacer vers la gauche\n");
        IO.print("6 : Vous déplacer vers la droite\n");
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
        }
    }
}
