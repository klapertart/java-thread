package klapertart.lab.thread;

import org.junit.jupiter.api.Test;

/**
 * @author kurakuraninja
 * @since 23/12/22
 */

public class CommunicationThreadTest {

    private String message = null;

    // test manual menggunakan sharing variable
    @Test
    void testManual() throws InterruptedException {

        Thread thread1 = new Thread(() -> {
           while (message == null){
               //wait
           }
            System.out.println(message);
        });

        Thread thread2 = new Thread(() -> {
           message = "Otong Sunandar";
        });

        thread2.start();
        thread1.start();

        thread2.join();
        thread1.join();

    }

    // test menggunakan wait & notify
    @Test
    void testWaitNotify() throws InterruptedException {

        Object lock = new Object();

        Thread thread1 = new Thread(() -> {
            synchronized (lock){
                try {
                    lock.wait();
                    System.out.println(message);
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (lock){
                message = "Otong Sunandar";
                lock.notify();
            }
        });

        thread1.start();
        thread2.start();

        thread2.join();
        thread1.join();

    }

    // test menggunakan wait & notifyAll
    @Test
    void testWaitNotifyAll() throws InterruptedException {

        Object lock = new Object();

        Thread thread1 = new Thread(() -> {
            synchronized (lock){
                try {
                    lock.wait();
                    System.out.println(message);
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
            }
        });

        Thread thread3 = new Thread(() -> {
            synchronized (lock){
                try {
                    lock.wait();
                    System.out.println(message);
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (lock){
                message = "Otong Sunandar";
                lock.notifyAll();
            }
        });

        thread1.start();
        thread3.start();

        thread2.start();

        thread1.join();
        thread3.join();

        thread2.join();

    }
}
