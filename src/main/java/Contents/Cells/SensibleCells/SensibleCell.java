package Contents.Cells.SensibleCells;

import Contents.Cells.Cell;
import Contents.Cells.InfectedCell;
import Contents.Virus.Virus;

import java.lang.reflect.InvocationTargetException;

/**
 * Cells than can be infected
 */
public abstract class SensibleCell extends Cell {
    public SensibleCell(int immunityLevel) {
        super(immunityLevel);
    }

    @Override
    public InfectedCell fuse(Virus virus) {
        try {
            return new InfectedCell(immunityLevel, virus.getClass().getConstructor().newInstance());
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        }
    }
}
