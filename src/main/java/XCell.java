public class XCell extends Cellule {
    public XCell(int x, int y) {
        super(x, y);
    }

    @Override
    protected void show() {
        System.out.print(" X ");
        super.show();
    }
}
