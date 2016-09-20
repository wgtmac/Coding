package gwu.java.concurrent;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by wgtmac on 9/18/16.
 *
 * Control the permits of each phase.
 */
public class PhaserTest {

    private static class Candidate extends Thread {
        private Phaser phaser;

        public Candidate(Phaser phaser) {
            super();
            this.phaser = phaser;
        }

        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            try {
                Thread.sleep(1000);
                System.out.println(name + " took the GRE verbal test. [Total: "
                        + phaser.getRegisteredParties() + ", Phase: "
                        + phaser.getPhase() + "]");

                if (Thread.currentThread().getId() % 2 == 0) {
                    // drop the math test
                    phaser.arriveAndDeregister();

                    System.out.println(name + " dropped the GRE math test.");
                } else {
                    phaser.arriveAndAwaitAdvance();

                    Thread.sleep(1000);  // break time

                    if (Thread.currentThread().getId() % 3 == 0) {
                        System.out.println(name + " took the GRE math test and left early. [Total: "
                                + phaser.getRegisteredParties() + ", Phase: "
                                + phaser.getPhase() + "]");
                        phaser.arrive();   // without blocking
                    } else {
                        System.out.println(name + " took the GRE math test. [Total: "
                                + phaser.getRegisteredParties() + ", Phase: "
                                + phaser.getPhase() + "]");
                        phaser.arriveAndAwaitAdvance();  // blocked here
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int numCandidates = 10;
        Phaser phaser = new Phaser(numCandidates - 5) {
            @Override
            protected boolean onAdvance(int phase, int registeredParties) {
                if (phase == 0) {
                    System.out.println("[Main]: GRE verbal test is over.");
                } else if (phase == 1) {
                    System.out.println("[Main]: GRE math test is over.");
                }
                return super.onAdvance(phase, registeredParties);
            }
        };

        phaser.register();
        phaser.bulkRegister(4);

        Candidate[] candidates = new Candidate[numCandidates];
        for (int i = 0; i < candidates.length; ++i) {
            candidates[i] = new Candidate(phaser);
            candidates[i].start();
        }

        phaser.awaitAdvance(0);   // block wait for phase 0 finish
        System.out.println("Break time!");

        phaser.awaitAdvance(1);   // block wait for phase 1 finish
        System.out.println("Game over!");

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    phaser.awaitAdvanceInterruptibly(2);
                } catch (InterruptedException e) {
                    System.out.println("Current thread is interrupted");
                }
            }
        });
        thread.start();
        thread.interrupt();

        try {
            phaser.awaitAdvanceInterruptibly(2, 1, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

        System.out.println("Phaser is terminated? " + phaser.isTerminated());
        phaser.forceTermination();
        System.out.println("Phaser is terminated? " + phaser.isTerminated());
    }
}
