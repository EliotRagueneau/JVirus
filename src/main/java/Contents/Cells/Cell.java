package Contents.Cells;

import Contents.Content;
import Contents.Virus.Virus;

public abstract class Cell extends Content {
    protected final int immunityLevel;

    /**
     * @param immunityLevel Property of cells which rules fusion between cells AND infection time
     */
    public Cell(int immunityLevel) {
        super();
        this.immunityLevel = immunityLevel;
    }

    @Override
    public Content fuse(Cell cell) {
        if (immunityLevel < cell.immunityLevel) {
            return this;
        } else {
            return cell;
        }
    }

    @Override
    public abstract Content fuse(Virus virus);
}
