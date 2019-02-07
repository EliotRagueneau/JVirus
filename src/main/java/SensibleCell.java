public abstract class SensibleCell extends Cell {
    public SensibleCell(int x, int y, int immunityLevel) {
        super(x, y, immunityLevel);
    }

    public InfectedCell fuse(Virus virus) {
        return new InfectedCell(x, y, immunityLevel, virus);
    }
}
