package klapertart.lab.thread;

/**
 * @author kurakuraninja
 * @since 19/12/22
 */
public class Counter {
    private  long value = 0L;

    public  void increment(){
        value++;
    }

    public  long getValue(){
        return value;
    }
}
