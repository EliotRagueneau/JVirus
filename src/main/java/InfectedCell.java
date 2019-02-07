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
        return null;
    }

    @Override
    public void turn() {
        for (int i = virions.size() - 1; i >= 0; i--) {
            lifeSpan--;

            if (lifeSpan == 0) {
                explode();
            } else {
                Virus newVirus = virions.get(i);
                for (int nbNewVirus = 0; nbNewVirus < newVirus.getVirulence(); nbNewVirus++) {
                    virions.add(newVirus);
                }
            }
        }
    }

    private void explode() {

    }
}
