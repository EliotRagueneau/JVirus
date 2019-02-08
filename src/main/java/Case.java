import Content.Content;
import Content.Enums.Direction;
import Utils.IO;

public class Case {
    public final int x;
    public final int y;
    public final Content content;

    public Case(int x, int y, Content content) {
        this.x = x;
        this.y = y;
        this.content = content;
    }

    public void menu() {
        IO.print("Que voulez-vous faire avec cet élément ?\n");
        IO.print("8 : Vous déplacer vers le haut\n");
        IO.print("2 : Vous déplacer vers le bas\n");
        IO.print("4 : Vous déplacer vers la gauche\n");
        IO.print("6 : Vous déplacer vers la droite\n");
        int rep = IO.intInput();
        Map map = Game.getMap();
        switch (rep) {
            case 8:
                map.move(this, Direction.UP);
                break;
            case 2:
                map.move(this, Direction.DOWN);
                break;
            case 4:
                map.move(this, Direction.LEFT);
                break;
            case 6:
                map.move(this, Direction.RIGHT);
                break;
        }
    }
}
