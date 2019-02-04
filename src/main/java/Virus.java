public class Virus extends Content {
    public Virus(int x, int y) {
        super(x, y);
    }

    @Override
    protected void show() {
        System.out.print(" 0 ");
        super.show();
    }
}
