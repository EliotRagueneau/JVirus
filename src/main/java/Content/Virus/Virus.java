package Content.Virus;

import Content.Cell.Cell;
import Content.Content;
import Content.Enums.TurnOver;
import Content.Timed;
import Content.Info;
import Utils.IO;

public class Virus extends Content implements Timed, Cloneable, Info {
    protected int lifeSpan;
    protected final int virulence;
    protected final int infectionTime;

    @Override
    public void info() {
        IO.print(String.format("Ce virus vas mourir dans %d tours !\n", lifeSpan));
    }

    public Virus(int lifeSpan, int virulence, int infectionTime) {
        super();
        empty = false;
        symbol = '*';
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
