public class ZCell extends Cellule {

    public ZCell(int x, int y) {
        super(x, y, 0);
    }

    @Override
    protected void show() {
        System.out.print(" Z ");
    }
}
