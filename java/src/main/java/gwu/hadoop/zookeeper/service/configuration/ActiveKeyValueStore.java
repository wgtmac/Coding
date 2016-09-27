package gwu.hadoop.zookeeper.service.configuration;

import gwu.hadoop.zookeeper.ConnectionWatcher;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.Stat;

import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

/**
 * Created by wgtmac on 9/25/16.
 *
 * A distributed configuration service: keys are znode paths & values are strings
 *
 */
public class ActiveKeyValueStore extends ConnectionWatcher {
    private static final Charset CHARSET = Charset.forName("UTF-8");

    public void write(String path, String value)
            throws InterruptedException, KeeperException {

        // zk should be initialized already
        Stat stat = zk.exists(path, false);

        if (stat == null) {
            // create the znode if it doesn't exist
            zk.create(path, value.getBytes(CHARSET), ZooDefs.Ids.OPEN_ACL_UNSAFE,
                    CreateMode.PERSISTENT);
        } else {
            // set data to the znode
            zk.setData(path, value.getBytes(CHARSET), -1);
        }
    }

    public String read(String path, Watcher watcher)
            throws InterruptedException, KeeperException {
        Stat stat = null; //new Stat();  // get metadata back
        byte[] data = zk.getData(path, watcher, stat);
        return new String(data, CHARSET);
    }
}