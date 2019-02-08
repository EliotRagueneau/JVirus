import java.lang.invoke.MethodHandles;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class Content {
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

    protected Content() {
    }

    public boolean isEmpty() {
        return empty;
    }

    public void show() {
        System.out.print(" · ");
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

    public void menu(Case selectedCase) {
    }
}
