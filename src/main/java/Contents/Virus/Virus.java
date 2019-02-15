package Contents.Virus;

import Contents.Cells.Cell;
import Contents.Content;
import Contents.Enums.TurnCallBack;
import Contents.Interfaces.Info;
import Contents.Interfaces.Timed;
import Utils.IO;

public class Virus extends Content implements Timed, Cloneable, Info {
    /**
     * Maximum life possible
     */
    protected final int lifeSpan;
    /**
     * Number of new viruses produced by turn and by virus in infected cells
     */
    protected final int virulence;

    /**
     * Base time of infection of infected cells
     */
    protected final int infectionTime;
    /**
     * Decreasing integer initiated with lifeSpan that will kill the virus when reaching 0
     */
    protected int life;

    @Override
    public void info() {
        IO.print(String.format("Ce virus va mourir dans %d tours !\n", lifeSpan));
    }

    public Virus(int lifeSpan, int virulence, int infectionTime) {
        super();
        symbol = '+';
        this.lifeSpan = lifeSpan;
        this.life = lifeSpan;
        this.virulence = virulence;
        this.infectionTime = infectionTime;
    }

    public int getVirulence() {
        return virulence;
    }

    public int getInfectionTime() {
        return infectionTime;
    }

    @Override
    public Content fuse(Cell cell) {
        return cell.fuse(this);
    }

    @Override
    public Virus fuse(Virus virus) {
        return new Virus((this.lifeSpan + virus.lifeSpan) / 2,
                (this.virulence + virus.virulence) / 2,
                (this.infectionTime + virus.infectionTime) / 2);
    }

    /**
     * @return DIE or NOTHING
     */
    @Override
    public TurnCallBack turn() {
        life--;
        if (life <= 0) {
            return TurnCallBack.DIE;
        }
        return TurnCallBack.NOTHING;
    }
}
