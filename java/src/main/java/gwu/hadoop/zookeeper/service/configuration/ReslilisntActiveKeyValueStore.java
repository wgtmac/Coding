package gwu.hadoop.zookeeper.service.configuration;

import org.apache.zookeeper.KeeperException;

import java.util.concurrent.TimeUnit;

/**
 * Created by wgtmac on 9/26/16.
 */
public class ReslilisntActiveKeyValueStore extends ActiveKeyValueStore {

    private static int MAX_RETRIES = 5;
    private static int RETRY_PERIOD_SECONDS = 10;

    /**
     * write() method is IDEMPOTENT, it is OK to unconditionally retry it
     */
    @Override
    public void write (String path, String value)
            throws InterruptedException, KeeperException {
        int retries = 0;
        while (true) {
            try {
                write(path, value);
                return;
            } catch (KeeperException.SessionExpiredException e) {
                // unrecoverable exception
                throw e;
            } catch (KeeperException e) {
                if (retries++ == MAX_RETRIES) {
                    throw e;
                }
                // sleep then retry
                TimeUnit.SECONDS.sleep(RETRY_PERIOD_SECONDS);
            }
        }
    }
}
