package gwu.hadoop.zookeeper.service.configuration;

import org.apache.zookeeper.KeeperException;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by wgtmac on 9/26/16.
 */
public class ResilientConfigUpdater {

    public static final String PATH = "/config";
    private ReslilisntActiveKeyValueStore store;
    private Random random = new Random();

    public ResilientConfigUpdater(String hosts) throws IOException, InterruptedException {
        store = new ReslilisntActiveKeyValueStore();
        store.connect(hosts);
    }

    public void run() throws InterruptedException, KeeperException {
        while (true) {
            String value = random.nextInt(100) + "";
            store.write(PATH, value);
            System.out.printf("Set %s to %s\n", PATH, value);
            TimeUnit.SECONDS.sleep(random.nextInt(10));
        }
    }

    public static void main(String[] args) throws Exception {
        while (true) {
            try {
                ResilientConfigUpdater configUpdater =
                        new ResilientConfigUpdater("localhost:2181");
                configUpdater.run();
            } catch (KeeperException.SessionExpiredException e) {
                // start a new session
            } catch (KeeperException e) {
                // already retried, so exit
                e.printStackTrace();
                break;
            }
            /**
             * When the ZooKeeper object is created, it tries to connect to a
             * ZooKeeper server. If the connection fails or times out, then it
             * tries another server in the ensemble. If, after trying all of the
             * servers in the ensemble, it can’t connect, then it throws an
             * IOException.
             */
        }
    }
}
