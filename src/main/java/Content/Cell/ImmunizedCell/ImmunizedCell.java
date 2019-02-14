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
}
