package com.palak.mtp;

public class ThreadDemo1 {
    public static void main(String[] args) {

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Running runnable from thread name : "+ Thread.currentThread().getName());
            }
        });

        thread1.start();

        Thread thread2 = new CustomThread();
        thread2.start();
    }
}

class CustomThread extends Thread {
    @Override
    public void run() {
        System.out.println("Running custom thread : "+ Thread.currentThread().getName());
    }
}
