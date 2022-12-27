package klapertart.lab.thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author kurakuraninja
 * @since 26/12/22
 */
public class SemaphoreTest {

    @Test
    void test() throws InterruptedException {

        // membatasi pekerjaan / task yang bisa dilakukan oleh
        // sejumlah thread, contohnya hanaya 3 job/task yang bisa dilakukan per sekali proses
        // oleh 3 thread
        Semaphore semaphore = new Semaphore(3);


        ExecutorService executorService = Executors.newFixedThreadPool(100);

        for (int i = 0; i < 1000; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    Thread.sleep(1000);
                    System.out.println("Finish");
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }finally {
                    semaphore.release();
                }
            });
        }


        executorService.awaitTermination(1, TimeUnit.DAYS);

    }
}
