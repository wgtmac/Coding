package gwu.hadoop.zookeeper.group;

import gwu.hadoop.zookeeper.ConnectionWatcher;
import org.apache.zookeeper.KeeperException;

import java.util.List;

/**
 * Created by wgtmac on 9/22/16.
 */
public class ListGroup extends ConnectionWatcher {
    public void list(String groupName) throws KeeperException,
            InterruptedException {
        String path = "/" + groupName;
        try {
            List<String> children = zk.getChildren(path, false);

            if (children.isEmpty()) {
                System.out.printf("No members in group %s\n", groupName);
                System.exit(1);
            }

            for (String child : children) {
                System.out.println(child);
            }
        } catch (KeeperException.NoNodeException e) {
            System.out.printf("Group %s does not exist\n", groupName);
            System.exit(1);
        }
    }

    public static void main(String[] args) throws Exception {
        // zkCli.sh -server localhost ls /zoo
        ListGroup listGroup = new ListGroup();
        listGroup.connect("localhost:2181");
        listGroup.list("zoo");
        listGroup.close();
    }
}
