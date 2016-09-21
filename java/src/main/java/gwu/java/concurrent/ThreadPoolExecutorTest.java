package gwu.java.concurrent;

import java.util.concurrent.*;

/**
 * Created by wgtmac on 9/20/16.
 */
public class ThreadPoolExecutorTest {

    private static class Worker implements Runnable {
        @Override
        public void run() {
            try {
                String name = Thread.currentThread().getName();
                System.out.println("[" + name + "]: hi!");
                Thread.sleep(1000);
                System.out.println("[" + name + "]: bye!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static class ImmortalWorker implements Runnable {
        @Override
        public void run() {
            System.out.println("Long live worker is running.");
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("Interrupted!");
                    break;
                }
            }
        }
    }

    private static ThreadPoolExecutor createThreadPool(BlockingQueue<Runnable> workQueue) {
        int corePoolSize = 2, maxPoolSize = 4;
        long keepAliveTime = 2l;
        return new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveTime,
                TimeUnit.SECONDS, workQueue);
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor queuedThreadPool = createThreadPool(new LinkedBlockingDeque<>());
        ThreadPoolExecutor nonqueuedThreadPool = createThreadPool(new SynchronousQueue<>());

        Worker worker = new Worker();
        for (int i = 0; i < 2; ++i) {
            queuedThreadPool.execute(worker);      // wait, no new thread created
            nonqueuedThreadPool.execute(worker);   // create new thread, no task queued
        }

        System.out.println("Blocking Queue: " + queuedThreadPool.getQueue().size());
        System.out.println("Sync Queue: " + nonqueuedThreadPool.getQueue().size());
        System.out.println("Core: " + queuedThreadPool.getCorePoolSize());
        System.out.println("Max: " + queuedThreadPool.getMaximumPoolSize());

        queuedThreadPool.shutdown();
        // awaitTermination() should be called after shutdown() is called
        // awaitTermination() will not block if no task is running
        queuedThreadPool.awaitTermination(2l, TimeUnit.SECONDS);
        System.out.println();

        try {
            for (int i = 0; i < 5; ++i) {
                nonqueuedThreadPool.execute(worker);
            }
        } catch (RejectedExecutionException e) {
            System.err.println("Cannot process threads more than max cores");
        }

        Thread.sleep(3000);
        System.out.println();
        // active threads are reduced
        System.out.println(nonqueuedThreadPool.getCorePoolSize());

        ImmortalWorker worker1 = new ImmortalWorker();
        nonqueuedThreadPool.execute(worker1);
        nonqueuedThreadPool.shutdownNow();
    }

}
