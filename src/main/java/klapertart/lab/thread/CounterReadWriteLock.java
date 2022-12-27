package klapertart.lab.thread;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author kurakuraninja
 * @since 19/12/22
 */
public class CounterReadWriteLock {
    private  long value = 0L;
    final private ReadWriteLock lock = new ReentrantReadWriteLock();

    public  void increment(){
        try{
            lock.writeLock().lock();
            value++;
        }finally {
            lock.writeLock().unlock();
        }
    }

    public  long getValue(){
        try{
            lock.readLock().lock();
            return value;
        }finally {
            lock.readLock().unlock();
        }
    }
}
