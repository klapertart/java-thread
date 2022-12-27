package klapertart.lab.thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author kurakuraninja
 * @since 23/12/22
 */
public class FutureTest {

    @Test
    void testFuture() throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        Future<String> future = executor.submit(() -> {
            Thread.sleep(2000);
            return "Hi";
        });

        while (!future.isDone()){
            System.out.println("Waiting Result");
            Thread.sleep(1000);
        }

        System.out.println(future.get());
    }

    @Test
    void testCancelFuture() throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        Future<String> future = executor.submit(() -> {
            Thread.sleep(2000);
            return "Hi";
        });

        Thread.sleep(5000);

        future.cancel(true);
        System.out.println(future.get());
    }

}
