public abstract class Cell extends Content {
    protected int immunityLevel;

    public Cell(int immunityLevel) {
        empty = false;
        this.immunityLevel = immunityLevel;
    }

    public Cell fuse(Cell cell){
        if (immunityLevel < cell.immunityLevel){
            return this;
        } else {
            return cell;
        }
    }

    public abstract Cell fuse(Virus virus);
}
