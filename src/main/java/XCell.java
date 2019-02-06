public class XCell extends Cell {
    public XCell() {
        super(2);
    }

    @Override
    protected void show() {
        System.out.print(" X ");
    }

    @Override
    public Cell fuse(Virus virus) {
        return null;
    }
}
