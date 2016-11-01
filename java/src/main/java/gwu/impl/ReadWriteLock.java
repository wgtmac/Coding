package gwu.impl;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by wgtmac on 10/4/16.
 *
 * An implementation of ReadWriteLock based on Lock and Condition
 */
public class ReadWriteLock {

    private int readCount = 0;
    private boolean isWriting = false;

    private Lock lock = new ReentrantLock();
    private Condition occupied = lock.newCondition();

    public void lockRead() {
        // wait for write lock release
        lock.lock();
        while (isWriting) {
            try {
                occupied.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        readCount++;
        lock.unlock();
    }

    public void lockWrite() {
        // wait for write lock or read lock release
        lock.lock();
        while (isWriting || readCount > 0) {
            try {
                occupied.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        isWriting = true;
        lock.unlock();
    }

    // assume always call lockRead() before unlockRead()
    public void unlockRead() {
        lock.lock();
        if (readCount > 0)
            readCount--;
        if (readCount == 0)
            occupied.signalAll();
        lock.unlock();
    }

    public void unlockWrite() {
        lock.lock();
        isWriting = false;
        occupied.signalAll();
        lock.unlock();
    }

    public static void main(String[] args) throws InterruptedException {
        ReadWriteLock lock = new ReadWriteLock();

        /**
         * Multiple reads, no write
         */
        for (int i = 0; i < 5; ++i) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String name = Thread.currentThread().getName();
                    lock.lockRead();
                    System.out.printf("%s started reading\n", name);

                    try {
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.printf("%s stopped reading\n", name);
                    lock.unlockRead();
                }
            }).start();
        }

        /**
         * Writes are blocked first, then executed sequentially
         */
        for (int i = 0; i < 3; ++i) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String name = Thread.currentThread().getName();
                    lock.lockWrite();
                    System.out.printf("%s started writing\n", name);

                    try {
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.printf("%s stopped writing\n", name);
                    lock.unlockWrite();
                }
            }).start();
        }
    }
}
