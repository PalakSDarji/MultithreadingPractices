package com.palak.mtp.termination;

public class InterruptionDemo {
    public static void main(String[] args) {

        Thread thread = new Thread(new CustomRunnable());
        thread.start();
        System.out.println("Log1");
        thread.interrupt();
        System.out.println("Log2");
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
}
