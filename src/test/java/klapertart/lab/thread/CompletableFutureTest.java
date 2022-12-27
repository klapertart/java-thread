package klapertart.lab.thread;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.*;

/**
 * @author kurakuraninja
 * @since 23/12/22
 */
public class CompletableFutureTest {
    
    private SampleCompletableFuture completableFuture;

    @BeforeEach
    void setUp() {
        completableFuture = new SampleCompletableFuture();
    }

    @Test
    void testCompletableFuture1() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Future<String> future = completableFuture.getValue(executorService);

        Assertions.assertEquals("success",future.get());

    }

    @Test
    void testCompletableFuture2() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        List<Future<String>> futures = completableFuture.getListValue(executorService);

        while (true){
            int countRun = 0;
            for (Future<String> future: futures){
                if(future.isDone()){
                    countRun++;
                }
            }

            if (countRun == futures.size()){
                break;
            }
        }

        futures.forEach(future -> {
            try {
                System.out.println(future.get());
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });

    }

    @Test
    void testCompletionStage() throws InterruptedException, ExecutionException {

        CompletableFuture<String> future = completableFuture.getValueCF(Executors.newSingleThreadExecutor());

        CompletableFuture<String[]> future2 = future.thenApply(value -> value.toUpperCase())
                .thenApply(value -> value.split(" "));

        String[] strings = future2.get();

        for (String temp: strings){
            System.out.println(temp);
        }
    }
}
