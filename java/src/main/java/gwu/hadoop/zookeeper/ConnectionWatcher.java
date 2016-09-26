package gwu.hadoop.zookeeper;


import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Example class to connect to zookeeper service.
 */
public class ConnectionWatcher implements Watcher {
    private static final int SESSION_TIMEOUT = 5000;

    protected ZooKeeper zk; // connection between client and zookeeper service
    private CountDownLatch connectedSignal = new CountDownLatch(1);

    public void connect(String hosts) throws IOException, InterruptedException {
        // watcher receives callbacks from ZooKeeper
        zk = new ZooKeeper(hosts, SESSION_TIMEOUT, this);  // async call
        connectedSignal.await();
    }

    @Override
    public void process(WatchedEvent event) { // Watcher interface
        if (event.getState() == Event.KeeperState.SyncConnected) {
            connectedSignal.countDown();
        }
    }

    public void close() throws InterruptedException {
        zk.close();
    }

    protected ZooKeeper.States getState() {
        return zk.getState();
    }
}

