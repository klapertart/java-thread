package klapertart.lab.thread;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author kurakuraninja
 * @since 25/12/22
 */
public class AtomicTest {

    @Test
    void testCounterAtomic() throws ExecutionException, InterruptedException {
        CounterAtomic atomic = new CounterAtomic();

        ExecutorService executorService = Executors.newFixedThreadPool(1);
        List<CompletableFuture<Long>> futures = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            CompletableFuture<Long> future = new CompletableFuture<>();
            executorService.execute(() -> {
                for (int j = 0; j < 1000000; j++) {
                    atomic.increment();
                }
                future.complete(atomic.getValue().longValue());
            });
            futures.add(future);
        }

        // wait until done
        while (true){
            int totalDone = 0;
            for (CompletableFuture<Long> future: futures){
                if (future.isDone()){
                    totalDone++;
                }
            }
            if (totalDone == futures.size()){
                break;
            }
        }


        System.out.println(atomic.getValue());
        System.out.println("--");
        for (CompletableFuture<Long> future: futures){
            System.out.println(future.get());
        }

    }
}
