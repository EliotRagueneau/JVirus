package Content.Cell;

import Content.Enums.TurnOver;
import Content.Info;
import Content.Timed;
import Content.Virus.Virus;
import Utils.IO;

import java.util.Vector;

public class InfectedCell extends Cell implements Timed, Info {

    private final Vector<Virus> virions = new Vector<>();

    private int lifeSpan;

    @Override
    public void info() {
        IO.print(String.format("Cette cellule infectée possède %d virus en elle.\n", virions.size()));
        IO.print(String.format("Elle exploseras dans %d tours.\n", lifeSpan));
    }

    public InfectedCell(int immunityLevel, Virus pathogen) {
        super(immunityLevel);
        symbol = 'ʘ';
        virions.add(pathogen);
        lifeSpan = pathogen.getInfectionTime() + immunityLevel;
    }

    public InfectedCell fuse(Virus virus) {
        virions.add(virus);
        return this;
    }

    public Vector<Virus> getVirions() {
        return virions;
    }

    @Override
    public TurnOver turn() {
        lifeSpan--;
        if (lifeSpan <= 0) {
            return TurnOver.EXPLODE;
        } else {
            for (int i = virions.size() - 1; i >= 0; i--) {
                Virus virus = virions.get(i);
                for (int nbNewVirus = 0; nbNewVirus < virus.getVirulence(); nbNewVirus++) {
                    virions.add((Virus) virus.clone());
                }
            }
        }
        return TurnOver.NOTHING;
    }
}
