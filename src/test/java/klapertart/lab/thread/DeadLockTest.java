package klapertart.lab.thread;

import org.junit.jupiter.api.Test;

/**
 * @author kurakuraninja
 * @since 23/12/22
 */
public class DeadLockTest {

    @Test
    void testDeadLock()throws InterruptedException {

        Balance balance1 = new Balance(1_000_000);
        Balance balance2 = new Balance(1_000_000);

        Thread thread1 = new Thread(() -> {

            try {
                Balance.transfer(balance1,balance2,500_000);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }

        });

        Thread thread2 = new Thread(() -> {

            try {
                Balance.transfer(balance2,balance1,500_000);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }

        });


        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println(balance1.getValue());
        System.out.println(balance2.getValue());
    }
}
