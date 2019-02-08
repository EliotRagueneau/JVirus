package Content;

import Content.Cell.Cell;
import Content.Virus.Virus;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class Content implements Cloneable {
    protected boolean empty = true;
    protected boolean movable = true;
    protected char symbol = '·';

    public boolean isMovable() {
        return movable;
    }

    public void setMovable(boolean movable) {
        this.movable = movable;
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Content> getNInstances(int n) {
        List<Content> out = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            try {
                Content newChildInstance = getClass().getConstructor().newInstance();
                out.add(newChildInstance);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        return out;
    }

    public Content() {
    }

    public boolean isEmpty() {
        return empty;
    }

    public void show() {
        System.out.print(String.format(" %c ", symbol));
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

    public Content fuse(Cell cell) {
        return null;
    }

    public Content fuse(Virus virus) {
        return null;
    }

//    public void menu(Case selectedCase) {
//    }
}
