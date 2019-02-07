public abstract class Virus extends Content implements Timed {
    protected int lifeSpan;
    protected int virulence;
    protected int infectionTime;

    public Virus(int x, int y, int lifeSpan, int virulence, int infectionTime) {
        super(x, y);
        empty = false;
        this.lifeSpan = lifeSpan;
        this.virulence = virulence;
        this.infectionTime = infectionTime;
    }

    @Override
    protected void show() {
        System.out.print(" 0 ");
    }

    public int getVirulence() {
        return virulence;
    }

    public int getInfectionTime() {
        return infectionTime;
    }

    @Override
    public void turn() {
        lifeSpan--;
        if (lifeSpan == 0) {
            Game.getMap().replaceContent(x, y, new Content(x, y));
        }
    }

    public Content fuse(Cell cell){
        return cell.fuse(this);
    }
}
