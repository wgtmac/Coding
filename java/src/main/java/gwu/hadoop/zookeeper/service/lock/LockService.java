package gwu.hadoop.zookeeper.service.lock;

/**
 * Created by wgtmac on 9/26/16.
 */
public class LockService {
    /**
     * The pseudocode for lock acquisition is as follows:
     * 1. Create an ephemeral sequential znode named lock- under the lock znode
     *    (/leader), and remember its actual pathname (the return value of the
     *    create operation).
     * 2. Get the children (a list) of the lock znode and set a watch.
     * 3. If the path name of the znode created in step 1 has the lowest number
     *    of the children returned in step 2, then the lock has been acquired.
     *    Exit.
     * 4. Wait for the notification from the watch set in step2, and go to step2
     *    for next round leader election.
     */

    /**
     * Herd effect
     *
     * The “herd effect” refers to a large number of clients being notified of
     * the same event when only a small number of them can actually proceed.
     *
     * Improvement:
     *   a client needs to be notified only when the child znode (n) with the
     *   previous sequence number (n - 1) goes away, not when any child znode
     *   is deleted (or created)
     */

    /**
     * Creation failure
     *
     * Create operation fails due to connection loss, nonidempotent, cannot know
     * which child it creates if succeeded.
     *
     * Solution:
     *   getSessionId() is unique for each session.
     *   lock-<sessionId>-<sequenceNumber> as the ephemeral sequential znode
     */

    public static void main(String[] args) {
        // see a production-ready Lock in the zookeeper repository
        // /src/recipes/lock/src/java/org/apache/zookeeper/recipes/lock/WriteLock.java
    }

}
