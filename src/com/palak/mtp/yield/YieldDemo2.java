package com.palak.mtp.yield;

import java.util.concurrent.locks.LockSupport;

public class YieldDemo2 {

    Object lock = new Object();

    private void doIt(){
        Thread1 thread1 = new Thread1();
        Thread2 thread2 = new Thread2();
        thread1.start();
        thread2.start();
    }

    private class Thread1 extends Thread{
        @Override
        public void run() {
                synchronized (lock){
                    System.out.println(Thread.currentThread().getName() + " got lock");
                    for(int i = 0; i < 10000; i++){
                        System.out.println(Thread.currentThread().getName() + " printing " + i);
                        if(i > 10){
                            System.out.println(Thread.currentThread().getName() + " will yield");
                            Thread.yield();
                            System.out.println(Thread.currentThread().getName() + " yielding done");
                        }
                    }
                }
            }

    }

    private class Thread2 extends Thread{
        @Override
        public void run() {

            //even after calling yield from Thread1, this thread won't kick off as it requires lock
            //which is acquired by Thread1. Calling yield() does not release object monitor lock.
            synchronized (lock){
                System.out.println(Thread.currentThread().getName() + " got lock");
                for(int i = 0; i < 10000; i++){
                    System.out.println(Thread.currentThread().getName() + " printing " + i);
                }
            }
        }
    }
    public static void main(String[] args) {

        YieldDemo2 yieldDemo2 = new YieldDemo2();
        yieldDemo2.doIt();
    }
}
