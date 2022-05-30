package com.palak.mtp.sync.inc;

public class SyncDemo1 {
    public static void main(String[] args) {

        Counter counter = new Counter();

        Thread t1 = new Thread(() -> {

            for(int i=0;i<10000;i++){
                counter.incCount();
            }
        });

        Thread t2 = new Thread(() -> {

            for(int i=0;i<10000;i++){
                counter.decCount();
            }
        });

        try {
            t1.start();
            t2.start();

            t1.join();
            t2.join();

            System.out.println(counter.getCount());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
