package com.palak.mtp.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicDemo1 {
    public static void main(String[] args) {

        AtomicInteger atomicInteger = new AtomicInteger(0);

        IncThread incThread = new IncThread(atomicInteger);
        DecThread decThread = new DecThread(atomicInteger);

        incThread.start();
        decThread.start();

        try {
            incThread.join();
            decThread.join();
        }
        catch (InterruptedException e){

        }

        System.out.println("atomic integer: " + atomicInteger.get());
    }
}
