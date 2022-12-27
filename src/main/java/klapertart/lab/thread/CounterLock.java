package klapertart.lab.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author kurakuraninja
 * @since 19/12/22
 */
public class CounterLock {
    private  long value = 0L;

    final private Lock lock = new ReentrantLock();

    public  void increment(){
        try {
            lock.lock();
            value++;
        }finally {
            lock.unlock();
        }
    }

    public  long getValue(){
        return value;
    }
}
