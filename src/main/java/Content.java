public class Content {
    protected int y;

    protected int x;

    protected boolean empty = true;

//    public boolean isEmpty() {
//        return empty;
//    }

    protected Content(int x, int y) {
        this.y = y;
        this.x = x;
    }

    protected void show() {
        System.out.print(" · ");
    }

   /* protected void move(Direction dir) {
        switch (dir) {
            case UP:
                if (y > 0) {
                    y--;
                    Map map = Game.getMap();
                    Content target = map.selectContent(x, y);
                    if (!target.isEmpty()) {
                        this.fuse(target);
                    } else {
                        map.exchangePosition(this, target);
                    }
                }
        }
    }*/

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

//    protected void fuse(Content target) {
//    }
}
