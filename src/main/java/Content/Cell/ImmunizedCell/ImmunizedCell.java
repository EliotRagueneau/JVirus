package Content.Cell.ImmunizedCell;


import Content.Cell.Cell;
import Content.Cell.InfectedCell;
import Content.Content;
import Content.Virus.Virus;

public abstract class ImmunizedCell extends Cell {
    protected int life = 2;

    public ImmunizedCell() {
        super(999);
    }

    @Override
    public Content fuse(Virus virus) {
        life--;
        if (life == 0) {
            return new Content();
        }
        return this;
    }

    @Override
    public Content fuse(Cell cell) {
        if (cell instanceof InfectedCell) {
            life--;
            if (life == 0) {
                return new Content();
            }
            return this;
        } else {
            return super.fuse(cell);
        }
    }

//    @Override
//    public void menu(Case selectedCase) {
//        super.menu(selectedCase);
//        IO.print("Que voulez-vous faire avec cette cellule immunisée ?\n");
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
}
