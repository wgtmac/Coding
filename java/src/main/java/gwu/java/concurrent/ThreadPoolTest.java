package gwu.java.concurrent;

import java.util.concurrent.*;

/**
 * Created by wgtmac on 9/21/16.
 */
public class ThreadPoolTest {

    private static class GoodWorker implements Runnable {
        @Override
        public void run() {
            try {
                String name = Thread.currentThread().getName();
                System.out.println("Entered " + name);
                Thread.sleep(1000);
                System.out.println("Exited " + name);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static class BadWorker extends GoodWorker {
        @Override
        public void run() {
            super.run();
            System.out.println(1 / 0);
        }
    }

    private static class WorkerThreadFactory implements ThreadFactory {
        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName("Worker-" + Math.random());
            thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
                @Override
                public void uncaughtException(Thread t, Throwable e) {
                    System.err.println(t.getName() + ": " + e.getMessage());
                }
            });
            return thread;
        }
    }

    private static ThreadPoolExecutor create() {
        return new ThreadPoolExecutor(1, 1, 1L, TimeUnit.SECONDS,
                new SynchronousQueue<>(), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setName("Worker-" + Math.random());
                return thread;
            }
        }) {
            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                super.beforeExecute(t, r);
                System.out.println("Prepare to execute " + t.getName());
            }
        };
    }

    public static void uncaughtExceptionTest() throws InterruptedException {
        ThreadPoolExecutor executor = create();
        executor.execute(new GoodWorker());

        Thread.sleep(1500);

        executor.setThreadFactory(new WorkerThreadFactory());
        executor.execute(new BadWorker());

        executor.shutdown();
        executor.awaitTermination(1L, TimeUnit.MINUTES);
    }

    public static void rejectExecutionHandlerTest() throws InterruptedException {
        ThreadPoolExecutor executor = create();

        executor.setRejectedExecutionHandler(new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                System.err.println("A worker is rejected.");
            }
        });

        GoodWorker worker = new GoodWorker();
        executor.execute(worker);
        executor.execute(worker);

        executor.shutdown();
        executor.awaitTermination(1L, TimeUnit.MINUTES);
    }

    public static void allowsCoreThreadTimeOutTest() throws InterruptedException {
        ThreadPoolExecutor executor = create();
        System.out.println(executor.getPoolSize());

        executor.prestartAllCoreThreads();
        System.out.println(executor.getPoolSize());

        executor.execute(new GoodWorker());
        Thread.sleep(2000);

        executor.allowCoreThreadTimeOut(true);
        Thread.sleep(2000);
        System.out.println(executor.getPoolSize());

        executor.shutdown();
        executor.awaitTermination(1L, TimeUnit.MINUTES);
    }

    public static void rejectPolicyTest() throws InterruptedException {
        ThreadPoolExecutor executor = create();
        /**
         * ThreadPoolExecutor.AbortPolicy, ThreadPoolExecutor.CallerRunsPolicy,
         * ThreadPoolExecutor.DiscardOldestPolicy
         */
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());

        GoodWorker worker = new GoodWorker();
        executor.execute(worker);
        executor.execute(worker);
        executor.execute(worker);

        executor.shutdown();
        executor.awaitTermination(1L, TimeUnit.MINUTES);
    }

    public static void main(String[] args) throws InterruptedException {
        uncaughtExceptionTest();
        rejectExecutionHandlerTest();
        allowsCoreThreadTimeOutTest();
        rejectPolicyTest();
    }
}
