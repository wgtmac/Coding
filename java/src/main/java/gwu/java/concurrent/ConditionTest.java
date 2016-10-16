package gwu.java.concurrent;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by wgtmac on 9/15/16.
 *
 * Same lock on different operations.
 *
 * [Object]    wait  <-> notify
 * [Condition] await <-> signal
 */
public class ConditionTest {

    private static class BlockingQueue<T> {
        private Queue<T> queue;
        private int capacity;
        private Lock lock;
        private Condition isEmpty, isFull;
        private Semaphore producerSemaphore, consumerSemaphore;

        public BlockingQueue() {
            this.capacity = 2;
            this.queue = new LinkedList<T>();
            this.lock = new ReentrantLock();
            this.isEmpty = lock.newCondition();
            this.isFull = lock.newCondition();
            this.producerSemaphore = new Semaphore(2);
            this.consumerSemaphore = new Semaphore(1);
        }

        public void produce(T t) throws InterruptedException {
            producerSemaphore.acquire();
            lock.lock();
            while (capacity == queue.size())
                isFull.await();
            queue.offer(t);
            isEmpty.signalAll();
            lock.unlock();
            producerSemaphore.release();
        }

        public T consume() throws InterruptedException {
            consumerSemaphore.acquire();
            lock.lock();
            while (queue.isEmpty())
                isEmpty.await();
            T t = queue.poll();
            isFull.signalAll();
            lock.unlock();
            consumerSemaphore.release();
            return t;
        }
    }

    private static class Producer extends Thread {
        private BlockingQueue<Integer> blockingQueue;

        public Producer(BlockingQueue<Integer> blockingQueue) {
            this.blockingQueue = blockingQueue;
        }

        @Override
        public void run() {
            try {
                int num = (int)Thread.currentThread().getId();
                Thread.sleep(100 * num);
                blockingQueue.produce(num);
                System.out.println(Thread.currentThread().getName() + " produced " + num);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static class Consumer extends Thread {
        private BlockingQueue<Integer> blockingQueue;

        public Consumer(BlockingQueue<Integer> blockingQueue) {
            this.blockingQueue = blockingQueue;
        }

        @Override
        public void run() {
            try {
                int num = blockingQueue.consume();
                System.out.println(Thread.currentThread().getName() + " consumed " + num);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Integer> blockingQueue = new BlockingQueue<>();

        Consumer[] consumers = new Consumer[4];
        for (Consumer consumer : consumers) {
            consumer = new Consumer(blockingQueue);
            consumer.start();
        }

        Thread.sleep(1000);

        Producer[] producers = new Producer[4];
        for (Producer producer : producers) {
            producer = new Producer(blockingQueue);
            producer.start();
        }
    }
}
