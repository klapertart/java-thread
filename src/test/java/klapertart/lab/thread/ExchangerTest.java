package klapertart.lab.thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author kurakuraninja
 * @since 26/12/22
 */
public class ExchangerTest {
    @Test
    void test() throws InterruptedException {
        Exchanger<String> exchanger = new Exchanger<>();
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        executorService.execute(() -> {
            try {
                String message = "Data dari Thread 1";
                System.out.println("Thread 1: sent: " + message);
                String result = exchanger.exchange(message);
                System.out.println("Thread 1: receive: " + result);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        });

        executorService.execute(() -> {
            try {
                String message = "Data dari Thread 2";
                System.out.println("Thread 2: sent: " + message);
                String result = exchanger.exchange(message);
                System.out.println("Thread 2: receive: " + result);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        });

        executorService.awaitTermination(1, TimeUnit.DAYS);

    }
}
