package klapertart.lab.thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author kurakuraninja
 * @since 26/12/22
 */
public class CountDownLatchTest {

    @Test
    void test() throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(5);
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 4; i++) {
            executorService.execute(() -> {
                try {
                    System.out.println("Start Task");
                    Thread.sleep(1000);
                    System.out.println("Finish Task");
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }finally {
                    countDownLatch.countDown();
                }
            });
        }

        executorService.execute(() -> {
            try {
                countDownLatch.await();
                System.out.println("All Task Already Finished");
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        });

        executorService.awaitTermination(1, TimeUnit.DAYS);

    }
}
