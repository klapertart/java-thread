package klapertart.lab.thread;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

/**
 * @author kurakuraninja
 * @since 19/12/22
 */
public class ThreadTest {

    @Test
    void mainThread() {
        String thread = Thread.currentThread().getName();
        System.out.println(thread);
    }

    @Test
    void createThread(){
        Runnable runnable = () ->{
            System.out.println(Thread.currentThread().getName());
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }

    @Test
    void createThreadSleep() throws InterruptedException {

            Runnable runnable = () ->{
                try {
                    Thread.sleep(2_000);
                    System.out.println(Thread.currentThread().getName());
                }catch (InterruptedException exception){
                    Assertions.fail(exception);
                }
            };


        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(3_000);
        System.out.println("Program Selesai");
    }

    @Test
    void createThreadJoin() throws InterruptedException {

        Runnable runnable = () ->{
            try {
                Thread.sleep(2_000);
                System.out.println(Thread.currentThread().getName());
            }catch (InterruptedException exception){
                Assertions.fail(exception);
            }
        };


        Thread thread = new Thread(runnable);
        thread.start();
        System.out.println("Menunggu proses");
        thread.join();
        System.out.println("Program Selesai");
    }


    @Test
    void createThreadInterrupted() throws InterruptedException {

        Runnable runnable = () ->{
            for (int i = 0; i < 10; i++) {
//                if(Thread.interrupted()){
//                    return;
//                }
                try {
                    Thread.sleep(2_000);
                    System.out.println("Runnable " + i);
                }catch (InterruptedException exception){
                    return;
                }

            }
        };


        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(5_000);
        thread.interrupt();
        thread.join();
        System.out.println("Program Selesai");
    }

    @Test
    void createState() throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println(Thread.currentThread().getState());
            System.out.println(Thread.currentThread().getName());
            });
        System.out.println(thread.getState());
        thread.setName("Thread Hamka");

        thread.start();
        thread.join();
        System.out.println(thread.getState());
    }
}
