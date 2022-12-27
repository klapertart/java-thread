package klapertart.lab.thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author kurakuraninja
 * @since 23/12/22
 */
public class ThreadPoolTest {

    @Test
    void testThreadPool() {

        int minThread = 2;
        int maxThread = 6;
        int alive = 1;
        TimeUnit time = TimeUnit.MINUTES;

        ArrayBlockingQueue<Runnable> runnables = new ArrayBlockingQueue<>(100);

        ThreadPoolExecutor executor = new ThreadPoolExecutor(minThread, maxThread, alive, time, runnables);
    }

    @Test
    void testExecutor() throws InterruptedException{

        int minThread = 2;
        int maxThread = 6;
        int alive = 1;
        TimeUnit time = TimeUnit.MINUTES;

        ArrayBlockingQueue<Runnable> runnables = new ArrayBlockingQueue<>(100);

        ThreadPoolExecutor executor = new ThreadPoolExecutor(minThread, maxThread, alive, time, runnables);

        executor.execute(() -> {
            try {
                Thread.sleep(1000);
                System.out.println("Hello from threadpool : " + Thread.currentThread().getName());
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        });

        Thread.sleep(10000);
    }


    @Test
    void testShutdownThread() throws InterruptedException{

        int minThread = 2;
        int maxThread = 6;
        int alive = 1;
        TimeUnit time = TimeUnit.MINUTES;

        ArrayBlockingQueue<Runnable> runnables = new ArrayBlockingQueue<>(500);

        ThreadPoolExecutor executor = new ThreadPoolExecutor(minThread, maxThread, alive, time, runnables);

        for (int i = 0; i < 500; i++) {
            final int task = i;
            executor.execute(() -> {
                try {
                    Thread.sleep(2000);
                    System.out.println("Task " + task + " from threadpool " + Thread.currentThread().getName());
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
            });
        }

        executor.shutdown();

        Thread.sleep(10_000);
    }

    @Test
    void testRejectedHandler() throws InterruptedException{

        int minThread = 2;
        int maxThread = 6;
        int alive = 1;
        TimeUnit time = TimeUnit.MINUTES;

        ArrayBlockingQueue<Runnable> runnables = new ArrayBlockingQueue<>(100);

        LogRejectedExecutionHandler handler = new LogRejectedExecutionHandler();

        ThreadPoolExecutor executor = new ThreadPoolExecutor(minThread, maxThread, alive, time, runnables,handler);

        for (int i = 0; i < 500; i++) {
            final int task = i;
            executor.execute(() -> {
                try {
                    Thread.sleep(2000);
                    System.out.println("Task " + task + " from threadpool " + Thread.currentThread().getName());
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
            });
        }

        Thread.sleep(10_000);
    }


}
