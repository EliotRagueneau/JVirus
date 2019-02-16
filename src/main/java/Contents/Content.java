package Contents;

import Contents.Cells.Cell;
import Contents.Virus.Virus;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Object contained in the map.<br>
 * Directly generated : act as an empty  element of the map<br>
 * Subclass generated : act as subclass
 */
public class Content implements Cloneable {
    protected boolean movable = true;
    protected char symbol = '·';

    public Content() {
    }

    public boolean isMovable() {
        return movable;
    }

    public void setMovable(boolean movable) {
        this.movable = movable;
    }

    /**
     * Generate n new instances of contents
     *
     * @param n Number of instances to generate
     * @return An ArrayList containing the n new instances
     */
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

    /**
     * Show the content so that it's well fitted in the map.show()
     */
    public void show() {
        System.out.print(String.format(" %c ", symbol));
    }

    /**
     * @param target Cell to fuse with
     * @return Content born by the fusion of the instance with the target
     */
    public Content fuse(Content target) {
        if (target instanceof Cell) {
            return this.fuse((Cell) target);
        } else if (target instanceof Virus) {
            return this.fuse((Virus) target);
        } else {
            return this;
        }
    }

    /**
     * Methods that MUST be overwritten by children classes
     *
     * @param cell Cell to fuse with
     * @return Content born by the fusion of the instance with the cell
     */
    public Content fuse(Cell cell) {
        return null;
    }

    /**
     * Methods that MUST be overwritten by children classes
     *
     * @param virus Virus to fuse with
     * @return Content born by the fusion of the instance with the virus
     */
    public Content fuse(Virus virus) {
        return null;
    }

    /**
     * @see Cloneable
     */
    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
