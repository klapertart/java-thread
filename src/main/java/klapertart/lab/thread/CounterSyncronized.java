package klapertart.lab.thread;

/**
 * @author kurakuraninja
 * @since 19/12/22
 */
public class CounterSyncronized {
    private  long value = 0L;

    public synchronized void increment(){
        value++;
    }

    public  long getValue(){
        return value;
    }
}
