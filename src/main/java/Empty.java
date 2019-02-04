public class Empty extends Content {
    public Empty(int x, int y) {
        super(x, y);
    }

    @Override
    protected void show() {
        System.out.print(" · ");
        super.show();
    }
}
