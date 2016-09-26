package gwu.impl;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by wgtmac on 9/21/16.
 *
 * A basic unlimited thread pool
 */

public class BasicThreadPool {

    private static BasicThreadPool instance = null;
    private Queue<BasicThread> idleThreads;
    private int threadCounter;
    private boolean isShutdown = false;

    private BasicThreadPool() {
        idleThreads = new LinkedList<>();
        threadCounter = 0;
    }

    public int getThreadCount() {
        return this.threadCounter;
    }

    public synchronized static BasicThreadPool getInstance() {
        if (instance == null)
            instance = new BasicThreadPool();
        return instance;
    }

    protected synchronized void recycle(BasicThread thread) {
        if (!isShutdown)
            idleThreads.offer(thread);
        else
            thread.shutdown();
    }

    public synchronized void shutdown() {
        isShutdown = true;
        for (BasicThread thread : idleThreads)
            thread.shutdown();
    }

    public synchronized void execute(Runnable runnable) {
        if (idleThreads.isEmpty()) {
            threadCounter++;
            BasicThread thread = new BasicThread(
                    runnable, "BasicThread-" + threadCounter, this);
            thread.start();
        } else {
            BasicThread thread = idleThreads.poll();
            thread.setRunnable(runnable);
        }
    }

    private static class BasicThread extends Thread {
        private BasicThreadPool pool;
        private Runnable runnable;
        private boolean isShutdown = false;
        private boolean isIdle = false;

        public BasicThread(Runnable runnable, String name, BasicThreadPool pool) {
            super(name);
            this.runnable = runnable;
            this.pool = pool;
        }

        public void run() {
            while (!isShutdown) {
                isIdle = false;
                if (runnable != null)
                    runnable.run();

                // task is over
                isIdle = true;
                try {
                    pool.recycle(this);
                    synchronized (this) {
                        // wait for this thread itself be notified
                        this.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                isIdle = false;
            }
        }

        public synchronized void shutdown() {
            isShutdown = true;
            notifyAll();   // give up the wait and exit
        }

        public synchronized void setRunnable(Runnable runnable) {
            this.runnable = runnable;
            notifyAll();   // give up the wait and continue next task
        }

        public Runnable getRunnable() {
            return runnable;
        }

        public boolean isShutdown() {
            return isShutdown;
        }

        public boolean isIdle() {
            return isIdle;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        BasicThreadPool threadPool = BasicThreadPool.getInstance();
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("New task!");
            }
        });
        Thread.sleep(1000);
        threadPool.shutdown();
    }
}
