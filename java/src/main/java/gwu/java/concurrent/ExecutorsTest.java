package gwu.java.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * Created by wgtmac on 9/19/16.
 *
 * Factory class to create ThreadPoolExecutor
 */
public class ExecutorsTest {

    private static void reuse(ExecutorService executorService) {
        for (int i = 0; i < 5; ++i) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        String name = Thread.currentThread().getName();
                        System.out.println("Enter " + name);
                        Thread.sleep(1000);
                        System.out.println("Exit " + name);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool(new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setName("Thread-" + Math.random());
                return thread;
            }
        });

        reuse(executorService);
        Thread.sleep(1000);
        System.out.println();
        reuse(executorService);
        executorService.shutdown();

        Thread.sleep(1000);
        System.out.println();
        executorService = Executors.newFixedThreadPool(5);
        reuse(executorService);
        executorService.shutdown();

        Thread.sleep(1000);
        System.out.println();
        executorService = Executors.newSingleThreadExecutor();
        reuse(executorService);
        executorService.shutdown();
    }
}
