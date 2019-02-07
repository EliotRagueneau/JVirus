import java.lang.invoke.MethodHandles;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class Content {
    protected int y;

    protected int x;

    protected boolean empty = true;

    public static List<Content> getNInstances(int n) {
        List<Content> out = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            try {
                out.add((Content) MethodHandles.lookup().lookupClass().getConstructor().newInstance());
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        return out;
    }

    protected Content(int x, int y) {
        this.y = y;
        this.x = x;
    }

    public boolean isEmpty() {
        return empty;
    }

    public void show() {
        System.out.print(" · ");
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    protected void move(Direction dir) {
        Map map = Game.getMap();
        switch (dir) {
            case UP:
                if (y > 0) {
                    y--;
                    Content target = map.selectContent(x, y);
                    map.replaceContent(x, y, this.fuse(target));
                }
                break;
            case RIGHT:

        }
    }

    public Content fuse(Content target) {
        if (target instanceof Cell) {
            return this.fuse((Cell) target);
        } else if (target instanceof Virus) {
            return this.fuse((Virus) target);
        } else {
            return this;
        }
    }

    public Cell fuse(Cell cell) {
        return cell;
    }

    public Cell fuse(Virus virus) {
        return null;
    }
}
