package gwu.java.concurrent;

import java.util.concurrent.*;

/**
 * Created by wgtmac on 9/28/16.
 *
 * Very efficient to run
 */
public class ScheduledExecutorServiceTest {

    private static class Worker implements Callable<String> {
        @Override
        public String call() throws Exception {
            String name = Thread.currentThread().getName();
            System.out.printf("%s started\n", name);
            TimeUnit.SECONDS.sleep(1);
            System.out.printf("%s ended\n", name);
            return name;
        }
    }

    private static class Runner implements Runnable {
        @Override
        public void run()  {
            String name = Thread.currentThread().getName();
            System.out.printf("%s started at %d\n", name, System.currentTimeMillis());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("%s ended at %d\n", name, System.currentTimeMillis());
        }
    }

    public static void scheduleTest() {
        ScheduledThreadPoolExecutor executorService =
                new ScheduledThreadPoolExecutor(2);
        //new ScheduledThreadPoolExecutor(2);

        Worker worker = new Worker();
        long secondsToDelay = 5;
        ScheduledFuture<?> future1 = executorService.schedule(worker, secondsToDelay, TimeUnit.SECONDS);
        ScheduledFuture<?> future2 = executorService.schedule(worker, secondsToDelay, TimeUnit.SECONDS);

        try {
            System.out.println(future1.get());
            System.out.println(future2.get());

            ScheduledFuture<?> future = null;
            for (int i = 1; i < 5; ++i)
                future = executorService.schedule(worker, i, TimeUnit.SECONDS);

            System.out.printf("%d tasks are waiting.\n", executorService.getQueue().size());
            executorService.remove(executorService.getQueue().peek());
            executorService.remove((Runnable) future);
            System.out.printf("%d tasks are waiting.\n", executorService.getQueue().size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        executorService.shutdown();
    }


    public static void scheduleWithFixedOrDelayTest() throws InterruptedException {
        ScheduledThreadPoolExecutor executorService =
                (ScheduledThreadPoolExecutor)Executors.newSingleThreadScheduledExecutor();

        Runner runner = new Runner();
        long seconds2Start = 1;
        long seconds2StartNext = 2;
        executorService.scheduleAtFixedRate(runner, seconds2Start, seconds2StartNext, TimeUnit.SECONDS);

        executorService.setContinueExistingPeriodicTasksAfterShutdownPolicy(true);
        TimeUnit.SECONDS.sleep(10);
        executorService.shutdown();

        executorService = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(2);
        long interval = 2;
        executorService.scheduleWithFixedDelay(runner, seconds2Start, interval, TimeUnit.SECONDS);

        executorService.setRemoveOnCancelPolicy(true);
        TimeUnit.SECONDS.sleep(10);
        executorService.shutdown();
    }


    public static void main(String[] args) throws InterruptedException {
        scheduleTest();

        scheduleWithFixedOrDelayTest();
    }
}
