package gwu.impl;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by wgtmac on 10/4/16.
 *
 * An implementation of ReadWriteLock based on synchronized and wait/notify
 */
public class ReadWriteLock2 {

    private int readCount = 0;
    private boolean isWriting = false;

    public synchronized void lockRead() {
        // wait for write lock release
        while (isWriting) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        readCount++;
    }

    public synchronized void lockWrite() {
        // wait for write lock or read lock release
        while (isWriting || readCount > 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        isWriting = true;
    }

    // assume always call lockRead() before unlockRead()
    public synchronized void unlockRead() {
        if (readCount > 0)
            readCount--;
        if (readCount == 0)
            notifyAll();
    }

    public synchronized void unlockWrite() {
        isWriting = false;
        notifyAll();
    }

    public static void main(String[] args) throws InterruptedException {
        ReadWriteLock2 lock = new ReadWriteLock2();

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
