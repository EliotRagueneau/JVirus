public class ZCell extends Cell {

    public ZCell(int x, int y) {
        super(x, y, 0);
    }

    @Override
    protected void show() {
        System.out.print(" Z ");
    }

    @Override
    public Cell fuse(Virus virus) {
        return null;
    }
}
