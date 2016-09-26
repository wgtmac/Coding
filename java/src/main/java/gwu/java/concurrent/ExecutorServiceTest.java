package gwu.java.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by wgtmac on 9/25/16.
 */
public class ExecutorServiceTest {

    private static class Worker implements Callable<String> {
        @Override
        public String call() throws Exception {
            String name = Thread.currentThread().getName();
            System.out.println("Started " + name);
            if(Thread.currentThread().isInterrupted()) {
                throw new InterruptedException(name + " is interrupted.");
            }
            return "Finished " + name;
        }
    }

    private static void invokeAnyTest(ExecutorService executor) {
        List<Worker> list = new ArrayList<>();
        Worker worker = new Worker();
        for (int i = 0; i < 2; ++i)
            list.add(worker);
        try {
            // interrupts other running tasks
            System.out.println("Get result: " + executor.invokeAny(list));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        ExecutorService executor = new ThreadPoolExecutor(4, 6, Long.MAX_VALUE,
                TimeUnit.SECONDS, new LinkedBlockingDeque<>());

        invokeAnyTest(executor);
    }

}
