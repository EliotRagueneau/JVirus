package Content.Cell.SensibleCell;

import Content.Cell.Cell;
import Content.Cell.InfectedCell;
import Content.Virus.Virus;

import java.lang.reflect.InvocationTargetException;

public abstract class SensibleCell extends Cell {
    public SensibleCell(int immunityLevel) {
        super(immunityLevel);
    }

    public InfectedCell fuse(Virus virus) {
        try {
            return new InfectedCell(immunityLevel, virus.getClass().getConstructor().newInstance());
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        }
    }
}
