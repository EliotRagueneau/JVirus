package Contents.Cells;

import Contents.Content;
import Contents.Virus.Virus;

public abstract class Cell extends Content {
    /**
     * Rules fusion between cells AND infection time
     */
    protected final int immunityLevel;

    public Cell(int immunityLevel) {
        super();
        this.immunityLevel = immunityLevel;
    }

    /**
     * @see Content
     */
    @Override
    public Content fuse(Cell cell) {
        if (immunityLevel < cell.immunityLevel) {
            return this;
        } else {
            return cell;
        }
    }

    /**
     * @see Content
     */
    @Override
    public abstract Content fuse(Virus virus);
}
