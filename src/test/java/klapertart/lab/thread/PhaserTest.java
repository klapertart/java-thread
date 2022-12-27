package klapertart.lab.thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * @author kurakuraninja
 * @since 26/12/22
 */
public class PhaserTest {

    @Test
    void testCountDownLatch() throws InterruptedException {
        Phaser phaser = new Phaser();
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        phaser.bulkRegister(5);
        for (int i = 0; i < 5; i++) {
            executorService.execute(() -> {
                try {
                    System.out.println("Start Task");
                    Thread.sleep(1000);
                    System.out.println("End Task");
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }finally {
                    phaser.arrive();
                }
            });
        }

        executorService.execute(() -> {
            phaser.awaitAdvance(0);
            System.out.println("All Task Done");
        });

        executorService.awaitTermination(1, TimeUnit.DAYS);
    }

    @Test
    void testCyclicBarrier() throws InterruptedException {
        Phaser phaser = new Phaser();
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        phaser.bulkRegister(5);

        for (int i = 0; i < 5; i++) {
            executorService.execute(() -> {
                System.out.println("Waiting Task");
                phaser.arriveAndAwaitAdvance();
                System.out.println("Done");
            });
        }


        executorService.awaitTermination(1, TimeUnit.DAYS);

    }
}
