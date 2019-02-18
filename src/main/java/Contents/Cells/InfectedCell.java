package Contents.Cells;

import Contents.Content;
import Contents.Virus.Virus;
import Enums.TurnCallBack;
import Interfaces.Info;
import Interfaces.Timed;
import Utils.IO;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Result of the fusing of a sensible cell and a virus, will explode after some amount of time determined by
 * the infecting virus infectionTime property and the immunity level of the founding cell.<br>
 * When exploding, the viruses contained in virions
 */
public class InfectedCell extends Cell implements Timed, Info {

    /**
     * Viruses that grows inside the infected cell
     */
    private final List<Virus> virions;

    /**
     * Amount of time before death and explosion
     */
    private int lifeSpan;

    public InfectedCell(int immunityLevel, Virus pathogen) {
        super(-1);
        symbol = 'ʘ';
        virions = new ArrayList<>();
        virions.add(pathogen);
        lifeSpan = pathogen.getInfectionTime() + immunityLevel;
    }

    public List<Virus> getVirions() {
        return virions;
    }

    /**
     * @see Content
     */
    @Override
    public InfectedCell fuse(Virus virus) {
        virions.add(virus);
        return this;
    }

    /**
     * @see Info
     */
    @Override
    public void info() {
        IO.print(String.format("Cette cellule infectée possède %d virus en elle.\n", virions.size()));
        IO.print(String.format("Elle exploseras dans %d tours.\n", lifeSpan));
    }

    /**
     * @see TurnCallBack
     */
    @Override
    public TurnCallBack turn() {
        lifeSpan--;
        if (lifeSpan <= 0) {
            return TurnCallBack.EXPLODE;
        } else {
            for (int i = virions.size() - 1; i >= 0; i--) {
                Virus virus = virions.get(i);
                for (int nbNewVirus = 0; nbNewVirus < virus.getVirulence(); nbNewVirus++) {
                    virions.add((Virus) virus.clone());
                }
            }
        }
        return TurnCallBack.NOTHING;
    }
}
