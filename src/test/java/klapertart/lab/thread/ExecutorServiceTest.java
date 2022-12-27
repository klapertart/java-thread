package klapertart.lab.thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author kurakuraninja
 * @since 23/12/22
 */
public class ExecutorServiceTest {

    @Test
    void testExecutorService()throws InterruptedException {

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 100; i++) {
            executorService.execute(() -> {
                try {
                    Thread.sleep(1000);
                    System.out.println("Execute");
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
            });
        }

        Thread.sleep(5000);
    }
}
