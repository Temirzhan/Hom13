import java.util.concurrent.Semaphore;

abstract class Stage {
    protected int length;
    protected String description;
    Semaphore smp;
    public String getDescription() {
        return description;
    }
    public abstract void go(Car c);
}