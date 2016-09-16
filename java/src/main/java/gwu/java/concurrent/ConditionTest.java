package gwu.java.concurrent;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by wgtmac on 9/15/16.
 */
public class ConditionTest {

    private static class Buffer<T> {
        private Queue<T> queue;
        private int capacity;
        private Lock lock;
        private Condition isEmpty, isFull;
        private Semaphore producerSemaphore, consumerSemaphore;

        public Buffer() {
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
        private Buffer<Integer> buffer;

        public Producer(Buffer<Integer> buffer) {
            this.buffer = buffer;
        }

        @Override
        public void run() {
            try {
                int num = (int)Thread.currentThread().getId();
                Thread.sleep(100 * num);
                buffer.produce(num);
                System.out.println(Thread.currentThread().getName() + " produced " + num);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static class Consumer extends Thread {
        private Buffer<Integer> buffer;

        public Consumer(Buffer<Integer> buffer) {
            this.buffer = buffer;
        }

        @Override
        public void run() {
            try {
                int num = buffer.consume();
                System.out.println(Thread.currentThread().getName() + " consumed " + num);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Buffer<Integer> buffer = new Buffer<>();

        Consumer[] consumers = new Consumer[4];
        for (Consumer consumer : consumers) {
            consumer = new Consumer(buffer);
            consumer.start();
        }

        Thread.sleep(1000);

        Producer[] producers = new Producer[4];
        for (Producer producer : producers) {
            producer = new Producer(buffer);
            producer.start();
        }
    }
}
