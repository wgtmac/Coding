package gwu.java.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by wgtmac on 10/4/16.
 */
public class WaitTest {

    /**
     * Use Object wait/notify strategy
     */
    private static class ObjectWaiter {
        static boolean isReady = false;
        public synchronized void doSomething() {
            while (!isReady) {
                try {
                    // should get intrinsic lock of this
                    // active => suspending
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public synchronized void unblock() {
            isReady = true;
            this.notifyAll();
        }
    }

    /**
     * Use Lock await/signal strategy
     */
    private static class LockWaiter {
        static boolean isReady = false;
        private Lock lock = new ReentrantLock();
        private Condition cond = lock.newCondition();
        public void doSomething() {
            lock.lock();
            while (!isReady) {
                try {
                    // active => suspending
                    cond.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            lock.unlock();
        }

        public void unblock() {
            lock.lock();
            isReady = true;
            cond.signalAll();
            lock.unlock();
        }
    }

}
