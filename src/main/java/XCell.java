public class XCell extends ImmunizedCell {
    public XCell(int x, int y) {
        super(x, y);
    }

    public XCell() {
        super(0, 0);
    }

    @Override
    public void show() {
        System.out.print(" X ");
    }
}
