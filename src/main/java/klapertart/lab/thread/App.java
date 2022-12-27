package klapertart.lab.thread;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws InterruptedException {

        CounterSyncronized counter = new CounterSyncronized();
        CounterSyncronizedStatement counterSyncronizedStatement = new CounterSyncronizedStatement();
        ListNumber listNumber = new ListNumber();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1_000_000; i++) {
                    listNumber.increment(i);
                }
            }
        };

        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        Thread thread3 = new Thread(runnable);

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();

        System.out.println(listNumber.getNumbers().size());
        System.out.println(listNumber.getNumbers().get(2_000_001));
    }
}
