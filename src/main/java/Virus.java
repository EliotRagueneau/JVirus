public class Virus extends Movable {
    public Virus(int x, int y) {
        super(x, y);
    }

    @Override
    protected void show() {
        System.out.print(" 0 ");
    }
}
