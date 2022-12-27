package klapertart.lab.thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

/**
 * @author kurakuraninja
 * @since 26/12/22
 */
public class BlockingQueueTest {

    @Test
    void testArrayBlockingQueue() throws InterruptedException {
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(5);
        ExecutorService executorService = Executors.newFixedThreadPool(20);


        for (int i = 0; i < 10; i++) {
            final int index = i;
            executorService.execute(() -> {
                try {
                    queue.put("Data " + index);
                    System.out.println("Finish put data " + index);
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
            });
        }

        executorService.execute(() -> {
            while (true){
                try {
                    Thread.sleep(2000);
                    String value = queue.take();
                    System.out.println("Receieve data : " + value);
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
            }
        });

        executorService.awaitTermination(1, TimeUnit.DAYS);
    }

    @Test
    void testLinkedBlockingQueue() throws InterruptedException {
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();
        ExecutorService executorService = Executors.newFixedThreadPool(20);


        for (int i = 0; i < 10; i++) {
            final int index = i;
            executorService.execute(() -> {
                try {
                    queue.put("Data " + index);
                    System.out.println("Finish put data " + index);
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
            });
        }

        executorService.execute(() -> {
            while (true){
                try {
                    Thread.sleep(2000);
                    String value = queue.take();
                    System.out.println("Receieve data : " + value);
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
            }
        });

        executorService.awaitTermination(1, TimeUnit.DAYS);
    }
}
