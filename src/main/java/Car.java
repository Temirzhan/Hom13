import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

class Car implements Runnable {
    private static int CARS_COUNT;
    private Race race;
    private int speed;
    private String name;
    static private CyclicBarrier cyclicBarrier;
    static private CountDownLatch countDownLatch;
    static String hero = "";
    public String getName() {
        return name;
    }
    public int getSpeed() {
        return speed;
    }
    public Car(Race race, int speed,CyclicBarrier cb, CountDownLatch cd) {
        cyclicBarrier = cb;
        countDownLatch = cd;
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }
    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));

            Car.cyclicBarrier.await();
            System.out.println(this.name + " готов");
            countDownLatch.countDown();
            Car.cyclicBarrier.await();
            for (int i = 0; i < race.getStages().size(); i++) {
                race.getStages().get(i).go(this);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            countDownLatch.countDown();
            if(hero.isEmpty())
                hero = this.name;
        }

    }
}