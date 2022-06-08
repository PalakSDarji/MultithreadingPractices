package com.palak.mtp.yield;

public class YieldDemo1 {
    public static void main(String[] args) throws InterruptedException {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while(i<10000){
                    i++;
                    System.out.println("printing i : " + i + " from " + Thread.currentThread().getName());

                    if(Thread.currentThread().getName().equals("Thread-0")){
                        Thread.yield();
                    }
                }
            }
        };

        Thread t1 = new Thread(runnable);
        t1.setPriority(Thread.MAX_PRIORITY);
        Thread t2 = new Thread(runnable);
        t1.setPriority(Thread.MIN_PRIORITY);

        t1.start();
        t2.start();

    }
}
