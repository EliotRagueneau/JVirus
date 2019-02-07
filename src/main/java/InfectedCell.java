import java.util.Vector;

public class InfectedCell extends Cell implements Timed {

    private Vector<Virus> virions = new Vector<>();

    private int lifeSpan;

    public InfectedCell(int x, int y, int immunityLevel, Virus pathogen) {
        super(x, y, immunityLevel);
        virions.add(pathogen);
        lifeSpan = pathogen.getInfectionTime() + immunityLevel;
    }

    public InfectedCell fuse(Virus virus) {
        virions.add(virus);
        return this;
    }

    @Override
    public void turn() {
        lifeSpan--;

        if (lifeSpan == 0) {
            explode();

        } else {

            for (int i = virions.size() - 1; i >= 0; i--) {
                Virus virus = virions.get(i);
                for (int nbNewVirus = 0; nbNewVirus < virus.getVirulence(); nbNewVirus++) {
                    virions.add(virus);
//                    Virus a = (Virus) clone(virus);
                }
            }
        }
    }

    private void explode() {

    }
}
