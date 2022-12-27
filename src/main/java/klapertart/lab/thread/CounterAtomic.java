package klapertart.lab.thread;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author kurakuraninja
 * @since 25/12/22
 */
public class CounterAtomic {
    private AtomicLong value = new AtomicLong(0L);

    public void increment(){
        value.incrementAndGet();
    }

    public AtomicLong getValue(){
        return value;
    }
}
