import java.lang.reflect.InvocationTargetException;

public abstract class SensibleCell extends Cell {
    public SensibleCell(int x, int y, int immunityLevel) {
        super(x, y, immunityLevel);
    }

    public InfectedCell fuse(Virus virus) {
        try {
            return new InfectedCell(x, y, immunityLevel, virus.getClass().getConstructor(new Class[]{int.class, int.class}).newInstance(x, y));
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        }
    }
}
