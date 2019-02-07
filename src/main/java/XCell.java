public class XCell extends ImmunizedCell {
    public XCell(int x, int y) {
        super(x, y);
    }

    @Override
    protected void show() {
        System.out.print(" X ");
    }
}
