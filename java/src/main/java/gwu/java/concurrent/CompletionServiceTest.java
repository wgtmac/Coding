package gwu.java.concurrent;

import java.util.concurrent.*;

/**
 * Created by wgtmac on 9/25/16.
 *
 * To tackle with blocking call Future.get()
 */
public class CompletionServiceTest {
    private static class CallableWorker implements Callable<String> {
        @Override
        public String call() throws Exception {
            String name = Thread.currentThread().getName();
            System.out.println(name + " starts working.");
            Thread.sleep(2000);
            System.out.println(name + " finishes working.");
            return name + " is returned.";
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Executor executor = Executors.newCachedThreadPool();

        // creates a CompletionService
        CompletionService service = new ExecutorCompletionService(executor);

        CallableWorker worker = new CallableWorker();

        for (int i = 0; i < 3; ++i)
            service.submit(worker);

        System.out.println(service.poll());  // non-blocking

        for (int i = 0; i < 2; ++i)
            System.out.println(service.take().get());

        System.out.println(service.poll(5L, TimeUnit.SECONDS).get());
    }
}
