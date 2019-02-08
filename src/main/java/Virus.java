import Utils.IO;

public abstract class Virus extends Content implements Timed {
    protected int lifeSpan;
    protected int virulence;
    protected int infectionTime;

    public Virus(int lifeSpan, int virulence, int infectionTime) {
        super();
        empty = false;
        this.lifeSpan = lifeSpan;
        this.virulence = virulence;
        this.infectionTime = infectionTime;
    }

    public static void wrongSelect() {
        IO.print("Veuillez sélectionner un virus !\n");
    }

    public int getVirulence() {
        return virulence;
    }

    public int getInfectionTime() {
        return infectionTime;
    }

    @Override
    public void show() {
        IO.print(" 0 ");
    }

    public Cell fuse(Cell cell) {
        return cell.fuse(this);
    }

    @Override
    public TurnOver turn() {
        lifeSpan--;
        if (lifeSpan == 0) {
            return TurnOver.DIE;
        }
        return null;
    }

    public void menu(Case selectedCase) {
        super.menu(selectedCase);
        IO.print("Que voulez-vous faire avec ce virus ?\n");
        IO.print("8 : Vous déplacer vers le haut\n");
        IO.print("2 : Vous déplacer vers le bas\n");
        IO.print("4 : Vous déplacer vers la gauche\n");
        IO.print("6 : Vous déplacer vers la droite\n");
        int rep = IO.intInput();
        Map map = Game.getMap();
        switch (rep) {
            case 8:
                map.move(selectedCase, Direction.UP);
                break;
            case 2:
                map.move(selectedCase, Direction.DOWN);
                break;
            case 4:
                map.move(selectedCase, Direction.LEFT);
                break;
            case 6:
                map.move(selectedCase, Direction.RIGHT);
                break;
        }
    }
}
