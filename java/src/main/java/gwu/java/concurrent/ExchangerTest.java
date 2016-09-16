package gwu.java.concurrent;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by wgtmac on 9/15/16.
 */
public class ExchangerTest {

    private static class Sender extends Thread {
        private Exchanger<String> exchanger;

        public Sender(Exchanger<String> exchanger) {
            super();
            this.exchanger = exchanger;
        }

        @Override
        public void run() {
            try {
                System.out.println(exchanger.exchange("Hello"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static class Receiver extends Thread {
        private Exchanger<String> exchanger;

        public Receiver(Exchanger<String> exchanger) {
            super();
            this.exchanger = exchanger;
        }

        @Override
        public void run() {
            try {
                System.out.println(exchanger.exchange("Hi", 1, TimeUnit.SECONDS));
                System.out.println(exchanger.exchange("Are you there?", 1, TimeUnit.SECONDS));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();
        Sender sender = new Sender(exchanger);
        Receiver receiver = new Receiver(exchanger);
        sender.start();
        receiver.start();
    }

}
