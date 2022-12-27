package klapertart.lab.thread;

/**
 * @author kurakuraninja
 * @since 23/12/22
 */



public class Balance {

    private long value;

    public Balance(long value) {
        this.value = value;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public static void transferDeadLock(Balance from, Balance to, long value) throws InterruptedException{
        synchronized (from){
            Thread.sleep(1_000);
            synchronized (to){
                Thread.sleep(2_000);
                from.setValue(from.getValue()-value);
                to.setValue(to.getValue()+value);
            }
        }
    }

    public static void transfer(Balance from, Balance to, long value) throws InterruptedException{
        synchronized (from){
            Thread.sleep(1_000);
            from.setValue(from.getValue()-value);
        }
        synchronized (to){
            Thread.sleep(1_000);
            to.setValue(to.getValue()+value);
        }
    }
}
