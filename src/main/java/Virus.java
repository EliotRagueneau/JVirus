public abstract class Virus extends Content {
    protected Virus(int x, int y) {
        super(x, y);
        empty = false;
    }

    @Override
    protected void show() {
        System.out.print(" 0 ");
    }
}
