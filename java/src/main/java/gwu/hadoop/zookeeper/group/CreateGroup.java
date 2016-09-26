package gwu.hadoop.zookeeper.group;


import gwu.hadoop.zookeeper.ConnectionWatcher;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;

/**
 * Example to create a znode representing a group in zookeeper
 *
 * Think of zookeeper as a file system, each znode can be a file or a directory
 *
 * Ephemeral node: check availability of distributed resources
 * Sequential node: impose a global ordering, a distributed lock
 */
public class CreateGroup extends ConnectionWatcher {

    public void create(String groupName) throws KeeperException, InterruptedException {
        String path = "/" + groupName;
        // ephemeral or persistent znode: delete as client disconnects
        // to zookeeper or not
        String createdPath = zk.create(path, null/*data*/,
                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println("Created " + createdPath);
    }

    public static void main(String[] args) throws Exception {
        // zkServer.sh stop
        CreateGroup createGroup = new CreateGroup();
        createGroup.connect("localhost:2181");
        createGroup.create("zoo");
        createGroup.close();
    }
}
