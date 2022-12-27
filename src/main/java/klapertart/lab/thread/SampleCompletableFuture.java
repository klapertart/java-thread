package klapertart.lab.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * @author kurakuraninja
 * @since 23/12/22
 */
public class SampleCompletableFuture {

    public Future<String> getValue(ExecutorService executorService){
        CompletableFuture<String> future = new CompletableFuture<>();

        executorService.execute(() -> {
            try {
                Thread.sleep(3000);
                future.complete("success");
            } catch (InterruptedException exception) {
                future.completeExceptionally(exception);
            }
        });

        return future;
    }

    public CompletableFuture<String> getValueCF(ExecutorService executorService){
        CompletableFuture<String> future = new CompletableFuture<>();

        executorService.execute(() -> {
            try {
                Thread.sleep(3000);
                future.complete("Otong Sunandar");
            } catch (InterruptedException exception) {
                future.completeExceptionally(exception);
            }
        });

        return future;
    }

    public List<Future<String>> getListValue(ExecutorService executorService){
        List<Future<String>> futures = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            final CompletableFuture<String> future = new CompletableFuture<>();
            final int task = i;
            executorService.execute(() -> {
                try {
                    Thread.sleep(3000);
                    future.complete("Success task " + task + " using thread pool " + Thread.currentThread().getName());
                } catch (InterruptedException exception) {
                    future.completeExceptionally(exception);
                }
            });

            futures.add(future);
        }

        return futures;
    }

}
