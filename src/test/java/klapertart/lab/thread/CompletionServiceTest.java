package klapertart.lab.thread;

import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @author kurakuraninja
 * @since 25/12/22
 */
public class CompletionServiceTest {

    private Random random = new Random();

    @Test
    void testCompletionService()throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        ExecutorCompletionService<String> completionService = new ExecutorCompletionService<>(executor);


        Executors.newSingleThreadExecutor().execute(() -> {
            for (int i = 0; i < 100; i++) {
                final int task = i;
                completionService.submit(() -> {
                    Thread.sleep(1000 + random.nextInt(1000));
                    return "task " + task;
                });
            }
        });

        Executors.newSingleThreadExecutor().execute(() -> {
            while (true){
                try {
                    Future<String> future = completionService.poll(5, TimeUnit.SECONDS);
                    if (future == null){
                        break;
                    }else{
                        System.out.println(future.get());
                    }
                } catch (InterruptedException | ExecutionException exception) {
                    break;
                }
            }

        });

        executor.awaitTermination(1,TimeUnit.DAYS);
    }
}
