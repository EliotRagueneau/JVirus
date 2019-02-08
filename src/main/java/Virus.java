import Utils.IO;

public class Virus extends Content implements Timed, Cloneable {
    protected int lifeSpan;
    protected int virulence;
    protected int infectionTime;

    public Virus(int lifeSpan, int virulence, int infectionTime) {
        super();
        empty = false;
        symbol = '9';
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
    public Content fuse(Cell cell) {
        return cell.fuse(this);
    }

    @Override
    public Virus fuse(Virus virus) {
        return new Virus((this.lifeSpan + virus.lifeSpan) / 2,
                (this.virulence + virus.virulence) / 2,
                (this.infectionTime + virus.infectionTime) / 2);
    }

    @Override
    public TurnOver turn() {
        lifeSpan--;
        symbol = (char) (lifeSpan + 48);
        if (lifeSpan == 0) {
            return TurnOver.DIE;
        }
        return TurnOver.NOTHING;
    }

//    public void menu(Case selectedCase) {
//        super.menu(selectedCase);
//        IO.print("Que voulez-vous faire avec ce virus ?\n");
//        IO.print("8 : Vous déplacer vers le haut\n");
//        IO.print("2 : Vous déplacer vers le bas\n");
//        IO.print("4 : Vous déplacer vers la gauche\n");
//        IO.print("6 : Vous déplacer vers la droite\n");
//        int rep = IO.intInput();
//        Map map = Game.getMap();
//        switch (rep) {
//            case 8:
//                map.move(selectedCase, Direction.UP);
//                break;
//            case 2:
//                map.move(selectedCase, Direction.DOWN);
//                break;
//            case 4:
//                map.move(selectedCase, Direction.LEFT);
//                break;
//            case 6:
//                map.move(selectedCase, Direction.RIGHT);
//                break;
//        }
//    }
}
