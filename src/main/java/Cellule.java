public abstract class Cellule extends Movable {
    protected int immunityLevel;

    public Cellule(int x, int y, int immunityLevel) {
        super(x, y);
        this.immunityLevel = immunityLevel;
    }
}
