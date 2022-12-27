package klapertart.lab.thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.*;
import java.util.function.BiConsumer;

/**
 * @author kurakuraninja
 * @since 27/12/22
 */
public class ConcurrentMapTest {

    @Test
    void test() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(100);
        ConcurrentHashMap<Integer, String> map = new ConcurrentHashMap<>();
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 100; i++) {
            final int index = i;
            executorService.execute(() -> {
                try {
                    map.putIfAbsent(index,"Data " + index);
                    Thread.sleep(1000);
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
                map.forEach((index, value) -> {
                    System.out.println("Index " + index + ", " + value);
                });
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        });

        executorService.awaitTermination(1, TimeUnit.DAYS);
    }
}
