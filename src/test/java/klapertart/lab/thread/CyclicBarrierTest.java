package klapertart.lab.thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

/**
 * @author kurakuraninja
 * @since 26/12/22
 */
public class CyclicBarrierTest {
    @Test
    void test() throws InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5);
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 4; i++) {
            executorService.execute(() -> {
                try {
                    System.out.println("Waiting");
                    cyclicBarrier.await();
                    System.out.println("Done Waiting");
                } catch (InterruptedException | BrokenBarrierException exception) {
                    exception.printStackTrace();
                }
            });
        }

        executorService.awaitTermination(1, TimeUnit.DAYS);
    }
}
