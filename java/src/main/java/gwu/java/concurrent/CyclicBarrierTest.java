package gwu.java.concurrent;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by wgtmac on 9/18/16.
 *
 * Same sort of threads waiting for each other.
 *
 * CyclicBarrier: await()
 */
public class CyclicBarrierTest {

    private static class Passenger extends Thread {
        private CyclicBarrier cyclicBarrier;
        private static Random rnd = new Random();

        public Passenger(CyclicBarrier cyclicBarrier) {
            super();
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            try {
                long wait = 0;
                synchronized(Passenger.class) {
                    wait = rnd.nextInt(4);
                }
                Thread.sleep(1000 * (1 + wait));
                System.out.println("Passenger " + name + " has boarded.");
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int capacity = 4;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(capacity, new Runnable() {
            @Override
            public void run() {
                System.out.println("Train is full and is leaving.");
            }
        });

        Passenger[] passengers = new Passenger[11];
        for (int i = 0; i < passengers.length; ++i) {
            passengers[i] = new Passenger(cyclicBarrier);
            passengers[i].start();
        }

        Thread.sleep(10000);
        System.out.println(cyclicBarrier.getNumberWaiting() + " passengers are waiting but no one comes.");
        cyclicBarrier.reset();
    }
}
