package gwu.hadoop.zookeeper.group;

import gwu.hadoop.zookeeper.ConnectionWatcher;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;

/**
 * Example code to create a member to join the group
 */
public class JoinGroup extends ConnectionWatcher {

    public void join(String groupName, String memberName)
            throws KeeperException, InterruptedException {
        String path = "/" + groupName + "/" + memberName;
        // maximum data size is 1MB
        String createdPath = zk.create(path, null/*data*/,
                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        System.out.println("Created " + createdPath);
    }

    public static void main(String[] args) throws Exception {
        JoinGroup joinGroup = new JoinGroup();
        joinGroup.connect("localhost:2181");
        joinGroup.join("zoo", "cat");
        joinGroup.join("zoo", "dog");
        joinGroup.join("zoo", "pig");

        Thread.sleep(Long.MAX_VALUE);
    }
}