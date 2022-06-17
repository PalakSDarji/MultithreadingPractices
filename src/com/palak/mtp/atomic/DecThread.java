package com.palak.mtp.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class DecThread extends Thread{

    private AtomicInteger count;
    private int limit;

    public DecThread(AtomicInteger count) {
        this.count = count;
    }

    @Override
    public void run() {
        while (limit < 1000){
            int i = count.decrementAndGet();
            System.out.println("DecThread atomic integer: " + i);
            limit++;
        }
    }
}
