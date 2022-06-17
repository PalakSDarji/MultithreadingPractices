package com.palak.mtp.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class IncThread extends Thread{

    private AtomicInteger count;
    private int limit;

    public IncThread(AtomicInteger count) {
        this.count = count;
    }

    @Override
    public void run() {
        while (limit < 1000){
            int i = count.incrementAndGet();
            System.out.println("IncThread atomic integer: " + i);
            limit++;
        }
    }
}
