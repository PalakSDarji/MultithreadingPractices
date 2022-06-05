package com.palak.mtp.semaphore;

import java.util.concurrent.Semaphore;

public class SemaphoreDemo2 {
    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(0);

        System.out.println(semaphore.availablePermits()); //0

        semaphore.release();
        semaphore.release();
        System.out.println(semaphore.availablePermits()); //2

        Thread t1 = new Thread(()->{
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            semaphore.release(4);// we already have two permits, releasing 4 more.
        });

        t1.start();
        t1.join(); //wait for the t1 to finish before finishing this main thread.
        semaphore.acquire(5); //will be blocked until above thread release 3 more permits.
        System.out.println(semaphore.availablePermits()); // when this execution will happen,
        // 5 permits will be given. leaving with 1.

    }
}
