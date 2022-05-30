package com.palak.mtp.sync.inc;

public class Counter {

    final Object obj1 = new Object();
    private int count;

    public void incCount(){
        synchronized(obj1){
            count++;
        }
    }

    public void decCount(){
        synchronized(obj1){
            count--;
        }
    }

    public int getCount() {
        return count;
    }
}
