package klapertart.lab.thread;

/**
 * @author kurakuraninja
 * @since 19/12/22
 */
public class CounterSyncronizedStatement {
    private  long value = 0L;

    public void increment(){
        synchronized(this){
            value++;
        }
    }

    public  long getValue(){
        return value;
    }
}
