import Utils.IO;

public abstract class Cell extends Content {
    protected int immunityLevel;

    public Cell(int immunityLevel) {
        super();
        empty = false;
        this.immunityLevel = immunityLevel;
    }

    public Cell fuse(Cell cell) {
        if (immunityLevel < cell.immunityLevel) {
            return this;
        } else {
            return cell;
        }
    }

    public abstract Cell fuse(Virus virus);

    public static void wrongSelect() {
        IO.print("Veuillez sélectionner une cellule !\n");
    }
}
