package com.palak.mtp.semaphore;

import java.util.concurrent.Semaphore;

public class SemaphoreDemo1 {
    public static void main(String[] args) throws InterruptedException {

        Semaphore semaphore = new Semaphore(1);

        Thread t1 = new Thread(()->{
            try {
                semaphore.acquire(); //This will acquire the permit. Now, Permit Count : 0
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();

        Thread t2 = new Thread(()->{
            semaphore.acquireUninterruptibly(); //Permit count is 0. Will wait forever until someone release.
            System.out.println(Thread.currentThread().getName() + " is interrupted ? : " +
                    Thread.currentThread().isInterrupted());
        });
        t2.start();

        Thread t3 = new Thread(()->{
            try {
                Thread.sleep(2000);
                t2.interrupt(); //before allowing t2 to acquire lock, lets interrupt it.
                //Since, acquireUninterruptibly is call, no effect of interrupt will ever happen.
                semaphore.release(); //lets release, so t2 can acquire.
                //when acquireUninterruptibly returns, interrupted status will be true. That is the power of acquireUninterruptibly.
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t3.start();
        t2.join(); //wait for t2 to complete
        t3.join(); //wait for t3 to complete
        System.out.println("t2 interrupted " + t2.isInterrupted());
    }
}
