package Contents.Cells.ImmunizedCells;


import Contents.Cells.Cell;
import Contents.Cells.InfectedCell;
import Contents.Content;
import Contents.Virus.Virus;

public abstract class ImmunizedCell extends Cell {
    /**
     * Number of virus and infected cells elimination before death
     */
    protected int life = 3;

    public ImmunizedCell() {
        super(999);
    }

    /**
     * @see Content
     */
    @Override
    public Content fuse(Virus virus) {
        life--;
        if (life == 0) {
            return new Content();
        }
        return this;
    }

    /**
     * @see Content
     */
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
}
