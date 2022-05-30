package com.palak.mtp.termination;

public class InterruptionDemo {
    public static void main(String[] args) {

        Thread thread = new Thread(new CustomRunnable());
        thread.start();
        System.out.println("Log1");
        thread.interrupt();
        System.out.println("Log2");

        Thread thread1 = new Thread(new LongRunnable());
        thread1.start();

        try {
            System.out.println("Putting this current thread on sleep for 40 ms");
            Thread.sleep(40);
            System.out.println("2 seconds over.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Lets interrupt");
        thread1.interrupt();
        System.out.println("Interruption done.");

        Thread thread2 = new Thread(new LongWhileRunnable());
        thread2.setDaemon(false);
        thread2.start();
        thread2.interrupt();
        boolean isInterrupted = thread2.isInterrupted();
        System.out.println("isInterrupted : "+ isInterrupted);
    }

    static class CustomRunnable implements Runnable {

        @Override
        public void run() {

                System.out.println("Log0");
            try {
                Thread.sleep(100000);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    static class LongRunnable implements Runnable {
        @Override
        public void run() {
            for(int i=0;i<100_000_000;i++){
                if(Thread.currentThread().isInterrupted()){
                    System.out.println("Thread is interuppted. Exit now.");
                    return;
                }
                System.out.println("Printing i: " + i);
            }
        }
    }

    static class LongWhileRunnable implements Runnable {
        @Override
        public void run() {
            while(true){
                if(Thread.currentThread().isInterrupted()){
                    System.out.println("Thread is interuppted. Exit now.");
                    return;
                }
            }
        }
    }
}
