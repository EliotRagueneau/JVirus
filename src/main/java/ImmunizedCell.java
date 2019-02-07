import Utils.IO;

public abstract class ImmunizedCell extends Cell{
    public ImmunizedCell(int x, int y) {
        super(x, y, 999);
    }

    @Override
    public ImmunizedCell fuse(Virus virus) {
        return this;
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
