package gwu.java.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by wgtmac on 9/25/16.
 */
public class ExecutorServiceTest {

    private static class WorkerA implements Callable<String> {
        @Override
        public String call() throws Exception {
            String name = Thread.currentThread().getName();
            System.out.println("Started " + name);
            return "Finished " + name;
        }
    }

    private static class WorkerB implements Callable<String> {
        @Override
        public String call() throws Exception {
            String name = Thread.currentThread().getName();
            System.out.println("Started " + name);
            while (!Thread.currentThread().isInterrupted()) {}
            System.err.println(name + " is interrupted.");
            // main thread will not handle this exception
            throw new InterruptedException(name + " is interrupted.");
        }
    }

    /**
     * invokeAny() guarantee 1 task and interrupts other slower tasks
     * It only applies to Callable
     * */
    private static void invokeAnyTest(ExecutorService executor) {
        List<Callable<String>> list = new ArrayList<>();
        list.add(new WorkerA());
        list.add(new WorkerB());
        try {
            // interrupts other running tasks
            System.out.println("Get result: " + executor.invokeAny(list));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            // if all tasks have failed, here will be caught
            e.printStackTrace();
        }
    }

    private static void invokeAllTest(ExecutorService executor) {
        List<Callable<String>> list = new ArrayList<>();
        WorkerA workerA = new WorkerA();
        list.add(workerA);
        list.add(workerA);

        try {
            // blocking call here
            List<Future<String>> futures = executor.invokeAll(list);
            for (Future<?> future : futures) {
                System.out.println(future.get());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = new ThreadPoolExecutor(2, 2, Long.MAX_VALUE,
                TimeUnit.SECONDS, new LinkedBlockingDeque<>());

        invokeAnyTest(executor);

        TimeUnit.SECONDS.sleep(2);

        invokeAllTest(executor);

        executor.shutdown();
        executor.awaitTermination(5L, TimeUnit.SECONDS);
    }

}
