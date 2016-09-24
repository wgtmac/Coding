package gwu.java.concurrent;

import java.util.concurrent.*;

/**
 * Created by wgtmac on 9/23/16.
 *
 * Callable vs Runnable
 * 1. Callable can return a value
 * 2. Callable can throw an exception
 */
public class CallableTest {

    private static class CallableWorker implements Callable<String> {
        @Override
        public String call() throws Exception {
            String name = Thread.currentThread().getName();
            System.out.println(name + " is working.");
            Thread.sleep(1000);
            System.out.println(name + " finishes working.");
            return name + " is returned.";
        }
    }

    private static class RunnableWorker implements Runnable {
        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            try {
                System.out.println(name + " is working.");
                Thread.sleep(1000);
                System.out.println(name + " finishes working.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            /**
             * Other exceptions need to be processed via ThreadFactory
             * UncaughtExceptionHandler
             */
        }
    }

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();

        CallableWorker callableWorker = new CallableWorker();
        RunnableWorker runnableWorker = new RunnableWorker();

        try {
            Future<String> future = executor.submit(callableWorker);
            System.out.println(future.get());   // blocking call

            System.out.println();

            Future<?> future1 = executor.submit(runnableWorker);  // without return
            // may cause InterruptedException
            System.out.println("Cancel task: " + future1.cancel(true));

            System.out.println();

            Future<String> future2 = executor.submit(runnableWorker, "Runnable is done.");
            System.out.println(future2.get() + " " + future2.isDone());

            // set RejectExecutionHandler
            ((ThreadPoolExecutor)executor).setRejectedExecutionHandler(new RejectedExecutionHandler() {
                @Override
                public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                    System.err.println(r.toString() + " is rejected");
                }
            });
            executor.shutdown();
            executor.submit(callableWorker);  // RejectExecutionHandler catches this
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
