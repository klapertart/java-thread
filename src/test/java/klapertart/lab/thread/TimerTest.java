package klapertart.lab.thread;

import org.junit.jupiter.api.Test;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author kurakuraninja
 * @since 23/12/22
 */
public class TimerTest {

    @Test
    void delayJob() throws InterruptedException {

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Delayed Job");
            }
        };

        Timer timer = new Timer();
        // di awal proses delay 2 detik,
        timer.schedule(task,2_000);

        Thread.sleep(3_000);


    }

    @Test
    void periodicJob() throws InterruptedException {

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Periodic Job");
            }
        };

        Timer timer = new Timer();
        // di awal proses delay 2 detik, selanjutnya diproses per 2 detik
        timer.schedule(task,2_000,2_000);

        Thread.sleep(10_000);


    }
}
