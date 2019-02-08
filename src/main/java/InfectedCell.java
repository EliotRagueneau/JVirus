import java.util.Vector;

public class InfectedCell extends Cell implements Timed {

    private Vector<Virus> virions = new Vector<>();

    private int lifeSpan;

    public InfectedCell(int immunityLevel, Virus pathogen) {
        super(immunityLevel);
        virions.add(pathogen);
        lifeSpan = pathogen.getInfectionTime() + immunityLevel;
    }

    public InfectedCell fuse(Virus virus) {
        virions.add(virus);
        return this;
    }

    @Override
    public TurnOver turn() {
        lifeSpan--;
        if (lifeSpan == 0) {
            return TurnOver.EXPLODE;
        } else {
            for (int i = virions.size() - 1; i >= 0; i--) {
                Virus virus = virions.get(i);
                for (int nbNewVirus = 0; nbNewVirus < virus.getVirulence(); nbNewVirus++) {
                    virions.add(virus);
//                    Virus a = (Virus) clone(virus);
                }
            }
        }
        return null;
    }

    private void explode() {

    }
}
