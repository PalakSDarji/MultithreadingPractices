package com.palak.mtp.reentrant;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteDemo {
    static int counter = 0;
    static int NUMBER_OF_READER_THREADS = 9;

    public static void main(String[] args) {

        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        Lock readLock = lock.readLock();
        Lock writeLock = lock.writeLock();

        Runnable runnable = () -> {
            writeLock.lock();
            for(int i=0;i<100;i++){
                counter++;
            }
            writeLock.unlock();
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
                readLock.lock();
                System.out.println("Counter: " + counter);
                readLock.unlock();
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
