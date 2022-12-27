package klapertart.lab.thread;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kurakuraninja
 * @since 21/12/22
 */
public class ListNumber {
    List<Integer> numbers = new ArrayList<>();

    public synchronized void increment(Integer value){
        numbers.add(value);
    }

    public List<Integer> getNumbers(){
        return numbers;
    }
}
