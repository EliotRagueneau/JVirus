package Content.Cell;

import Content.Content;
import Content.Virus.Virus;
import Utils.IO;

public abstract class Cell extends Content {
    protected final int immunityLevel;

    public Cell(int immunityLevel) {
        super();
        empty = false;
        this.immunityLevel = immunityLevel;
    }

    public Content fuse(Cell cell) {
        if (immunityLevel < cell.immunityLevel) {
            return this;
        } else {
            return cell;
        }
    }

    public abstract Content fuse(Virus virus);

    public static void wrongSelect() {
        IO.print("Veuillez sélectionner une cellule !\n");
    }
}
