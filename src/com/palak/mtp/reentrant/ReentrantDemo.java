package com.palak.mtp.reentrant;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantDemo {
    static int counter = 0;
    static int NUMBER_OF_READER_THREADS = 9;

    public static void main(String[] args) {

        ReentrantLock lock = new ReentrantLock();
        Runnable runnable = () -> {
            lock.lock();
            for(int i=0;i<100;i++){
                counter++;
            }
            lock.unlock();
        };

        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        Thread t3 = new Thread(runnable);

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Runnable readRunnable = () -> {
            for(int i=0;i<10000;i++){
                lock.lock();
                System.out.println("Counter: " + counter);
                lock.unlock();
            }
        };

        long start = System.currentTimeMillis();

        for(int i=0;i<NUMBER_OF_READER_THREADS;i++){

            Thread t = new Thread(readRunnable);
            t.start();
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        long end = System.currentTimeMillis();

        System.out.println("time taken while reading : " + (end - start));


    }
}
