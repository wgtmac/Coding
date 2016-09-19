package gwu.java.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by wgtmac on 9/18/16.
 *
 * Control steps among threads.
 */
public class CountDownLatchTest {

    private static class Student extends Thread {
        private CountDownLatch beginClassLatch, endClassLatch;
        public Student(CountDownLatch beginClassLatch, CountDownLatch endClassLatch) {
            super();
            this.beginClassLatch = beginClassLatch;
            this.endClassLatch = endClassLatch;
        }

        @Override
        public void run() {
            beginClassLatch.countDown();
            long count = beginClassLatch.getCount();
            System.out.println("[Student] " + Thread.currentThread().getName() +
                    " has arrived. " + count + " students are missing.");

            try {
                endClassLatch.await();
                System.out.println("[Student] " + Thread.currentThread().getName() +
                        " has left the classroom.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static class Teacher extends Thread {
        private CountDownLatch beginClassLatch, endClassLatch;

        public Teacher(CountDownLatch beginClassLatch, CountDownLatch endClassLatch) {
            super();
            this.beginClassLatch = beginClassLatch;
            this.endClassLatch = endClassLatch;
        }

        @Override
        public void run() {
            try {
                beginClassLatch.await(1, TimeUnit.SECONDS);
                long count = beginClassLatch.getCount();
                System.out.println("[Teacher] " + Thread.currentThread().getName() +
                        " has begun the class. " + count + " students are missing.");

                Thread.sleep(4000);
                System.out.println("[Teacher] " + Thread.currentThread().getName() +
                        " has ended the class.");
                endClassLatch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        CountDownLatch beginClassLatch = new CountDownLatch(5);
        CountDownLatch endClassLatch = new CountDownLatch(1);

        Student[] students = new Student[(int)beginClassLatch.getCount()];
        for (int i = 0; i < students.length; ++i)
            students[i] = new Student(beginClassLatch, endClassLatch);

        Teacher teacher = new Teacher(beginClassLatch, endClassLatch);
        teacher.start();

        Thread.sleep(2000);
        for (Student student : students)
            student.start();
    }
}
