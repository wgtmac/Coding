package gwu.java.concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by wgtmac on 9/26/16.
 */
public class DeadLockTest {
    private static class Communicator {
        private String name;

        public Communicator(String name) {
            this.name = name;
        }

        public synchronized void ping(Communicator other) {
            System.out.println(name + " ping " + other.name);
            other.pong(this);
        }

        private synchronized void pong(Communicator other) {
            System.out.println(name + " pong " + other.name);
        }
    }

    private static class CommunicatorV2 {
        private String name;
        private Lock lock = new ReentrantLock();

        public CommunicatorV2(String name) {
            this.name = name;
        }

        public void ping(CommunicatorV2 other) {
            lock.lock();
            System.out.println(name + " ping " + other.name);
            other.pong(this);
            lock.unlock();
        }

        public void pong(CommunicatorV2 other) {
            lock.lock();
            System.out.println(name + " pong " + other.name);
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        CommunicatorV2 a = new CommunicatorV2("A");
        CommunicatorV2 b = new CommunicatorV2("B");

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                    a.ping(b);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                    b.ping(a);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
