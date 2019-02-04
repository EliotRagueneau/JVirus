public abstract class Content {
    private int y;

    private int x;

    protected Content(int x, int y) {
        this.y = y;
        this.x = x;
    }

    protected void show() {

    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }
}
