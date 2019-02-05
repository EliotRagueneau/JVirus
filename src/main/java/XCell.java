public class XCell extends Cellule {
    public XCell(int x, int y) {
        super(x, y, 2);
    }

    @Override
    protected void show() {
        System.out.print(" X ");
    }
}
