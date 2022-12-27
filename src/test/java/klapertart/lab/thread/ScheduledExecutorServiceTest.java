package klapertart.lab.thread;

import org.junit.jupiter.api.Test;
import sun.awt.windows.ThemeReader;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author kurakuraninja
 * @since 25/12/22
 */
public class ScheduledExecutorServiceTest {

    @Test
    void testDelayJob() throws InterruptedException {

        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

        executorService.schedule(()->{
            System.out.println(Thread.currentThread().getName());
        },2, TimeUnit.SECONDS);

        Thread.sleep(3000);
    }

    @Test
    void testPeriodicJob() throws InterruptedException {

        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

        executorService.scheduleAtFixedRate(()->{
            System.out.println(Thread.currentThread().getName());
        },2, 2, TimeUnit.SECONDS);

        Thread.sleep(10000);
    }
}
