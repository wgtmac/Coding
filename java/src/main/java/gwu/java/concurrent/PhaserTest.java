package gwu.java.concurrent;

import java.util.concurrent.Phaser;

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

                    Thread.sleep(1000);
                    System.out.println(name + " took the GRE math test. [Total: "
                            + phaser.getRegisteredParties() + ", Phase: "
                            + phaser.getPhase() + "]");
                    phaser.arriveAndAwaitAdvance();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        int numCandidates = 10;
        Phaser phaser = new Phaser(numCandidates) {
            @Override
            protected boolean onAdvance(int phase, int registeredParties) {
                if (phase == 0) {
                    System.out.println("[Main]: GRE verbal test is over.");
                } else if (phase == 1) {
                    System.out.println("[Main]: GRE math test is over.");
                }
                return false;
            }
        };

        Candidate[] candidates = new Candidate[numCandidates];
        for (int i = 0; i < candidates.length; ++i) {
            candidates[i] = new Candidate(phaser);
            candidates[i].start();
        }
    }
}
