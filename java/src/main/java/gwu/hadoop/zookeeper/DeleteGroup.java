package gwu.hadoop.zookeeper;

import org.apache.zookeeper.KeeperException;

import java.util.List;

/**
 * Created by wgtmac on 9/22/16.
 */
public class DeleteGroup extends ConnectionWatcher {
    public void delete(String groupName) throws KeeperException,
            InterruptedException {
        String path = "/" + groupName;
        try {
            List<String> children = zk.getChildren(path, false);
            // no recursive deletion in zookeeper
            for (String child : children) {
                zk.delete(path + "/" + child, -1);  // -1 means regardless of version
            }
            zk.delete(path, -1);
        } catch (KeeperException.NoNodeException e) {
            System.out.printf("Group %s does not exist\n", groupName);
            System.exit(1);
        }
    }

    public static void main(String[] args) throws Exception {
        DeleteGroup deleteGroup = new DeleteGroup();
        deleteGroup.connect("localhost");
        deleteGroup.delete("zoo");
        deleteGroup.close();
    }
}
