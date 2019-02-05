public class YCell extends Cellule {
    public YCell(int x, int y) {
        super(x, y, 1);
    }

    @Override
    protected void show() {
        System.out.print(" Y ");
    }
}
