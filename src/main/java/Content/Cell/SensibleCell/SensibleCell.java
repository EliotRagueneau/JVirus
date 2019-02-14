package Content.Cell.SensibleCell;

import Content.Cell.Cell;
import Content.Cell.InfectedCell;
import Content.Virus.Virus;

public abstract class SensibleCell extends Cell {
    public SensibleCell(int immunityLevel) {
        super(immunityLevel);
    }

    public InfectedCell fuse(Virus virus) {
        return new InfectedCell(immunityLevel, new Virus(virus.getLifeSpan(), virus.getVirulence(), virus.getInfectionTime()));
    }
}
