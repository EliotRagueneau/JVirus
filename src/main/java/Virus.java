public abstract class Virus extends Content {
    protected Virus() {
        empty = false;
    }

    @Override
    protected void show() {
        System.out.print(" 0 ");
    }
}
