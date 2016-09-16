package gwu.java.concurrent;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Created by wgtmac on 9/15/16.
 */
public class SemaphoreTest {

    private static class SemaphoreThread extends Thread {
        private Semaphore semaphore;
        private int permit;
        private boolean isBlocking;


        public SemaphoreThread(Semaphore semaphore, int permit, boolean isBlocking) {
            super();
            this.semaphore = semaphore;
            this.permit = permit;
            this.isBlocking = isBlocking;
        }

        private void doSomething() throws InterruptedException {
            System.out.println(Thread.currentThread().getName() + " started, available: "
                    + semaphore.availablePermits() + ", waiting threads: "
                    + semaphore.getQueueLength());
            Thread.sleep(5000);
        }

        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            try {
                if (isBlocking) {
                    //semaphore.acquire(this.permit);
                    semaphore.acquireUninterruptibly(this.permit);
                    doSomething();
                    semaphore.release(this.permit);
                } else {
                    if (semaphore.tryAcquire(this.permit, 1, TimeUnit.SECONDS)) {
                        doSomething();
                        semaphore.release(this.permit);
                    } else {
                        System.out.println(name + " failed to acquire permit(s).");
                    }
                }

            } catch (InterruptedException e) {
                System.err.println(name + " is interrupted.");
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        boolean isFair = false;
        Semaphore semaphore = new Semaphore(2, false);
        SemaphoreThread a = new SemaphoreThread(semaphore, 2, true);
        SemaphoreThread b = new SemaphoreThread(semaphore, 1, true);
        SemaphoreThread c = new SemaphoreThread(semaphore, 1, false);
        a.start();
        b.start();
        c.start(); // c will fail to acquire permit
        Thread.sleep(2000);
        semaphore.release(2); // b will be unblocked
        //c.interrupt();
    }
}
