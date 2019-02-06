public class Content {
    protected boolean empty = true;

    public boolean isEmpty() {
        return empty;
    }

    protected Content() {}

    protected void show() {
        System.out.print(" · ");
    }

//    protected void move(Direction dir) {
//        switch (dir) {
//            case UP:
//                if (y > 0) {
//                    y--;
//                    Map map = Game.getMap();
//                    Content target = map.selectContent(x, y);
//                    if (!target.isEmpty()){
//                        fuse(target);
//                    } else {
//                        map.exchangePosition(this, target);
//                    }
//                }
//        }
//    }
}
