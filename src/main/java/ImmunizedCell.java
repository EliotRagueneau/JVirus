public abstract class ImmunizedCell extends Cell{
    public ImmunizedCell(int x, int y) {
        super(x, y, 999);
    }

    @Override
    public ImmunizedCell fuse(Virus virus) {
        return this;
    }
}
