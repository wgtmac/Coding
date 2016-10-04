package gwu.java.concurrent;

import java.util.concurrent.*;

/**
 * Created by wgtmac on 9/30/16.
 */
public class ForkJoinPoolTest {

    public static ForkJoinPool createForkJoinPool() {
        // parallelism equal to Runtime.availableProcessors()
        ForkJoinPool pool = new ForkJoinPool();
        return pool;
    }

    /**
     * RecursiveAction has no return value
     */
    private static class GenerateRange extends RecursiveAction{
        private int a, b;
        public GenerateRange(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        protected void compute() {
            String name = Thread.currentThread().getName();
            if (a == b)
                System.out.println(name + ": [" + a + "," + b + "]");
            else {
                int mid = a + (b - a) / 2;
                invokeAll(new GenerateRange(a, mid), new GenerateRange(mid + 1, b));
            }
        }
    }

    public static void recursiveActionInvokeTest(ForkJoinPool pool) {
        pool.submit(new GenerateRange(1, 10));
    }

    /**
     * RecursiveTask has return value
     */
    private static class Computer extends RecursiveTask<Integer> {
        private int a, b;
        public Computer(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        protected Integer compute() {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (a != b) {
                int mid = a + (b - a) / 2;
                ForkJoinTask<?> left =
                        new Computer(a, mid), right = new Computer(mid + 1, b);
                invokeAll(left, right);
                return (Integer) left.join() + (Integer) right.join();
            } else {
                return a;
            }
        }
    }

    public static void recursiveTaskTest(ForkJoinPool pool) {
        ForkJoinTask<?> task1 = pool.submit(new Computer(1, 10));
        try {
            // get() handles exception in main()
            System.out.println(task1.get());
            System.out.println("Block released ...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("Blocking ...");
        ForkJoinTask<?> task2 = pool.submit(new Computer(1, 6));
        // join() handles exception internally
        System.out.println(task2.join());
    }

    public static void main(String[] args) throws InterruptedException {
        ForkJoinPool pool = createForkJoinPool();

        //recursiveActionInvokeTest(pool);

        recursiveTaskTest(pool);

        // blocking call with return value
        Integer value = pool.invoke(new Computer(1,1));
        System.out.println(value);

        TimeUnit.SECONDS.sleep(10);

        pool.shutdownNow();
    }
}
