package com.palak.mtp.reentrant;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantConditionDemo {

    boolean isCompleted = false;
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    private void proceed(){
        Thread1 thread1 = new Thread1();
        Thread2 thread2 = new Thread2();

        thread1.start();
        thread2.start();
    }

    public static void main(String[] args) {
        ReentrantConditionDemo reentrantConditionDemo = new ReentrantConditionDemo();
        reentrantConditionDemo.proceed();
    }

    private class Thread1 extends Thread {

        @Override
        public void run() {
            try {
                lock.lock();
                while (!isCompleted){
                    System.out.println("Thread1 is going to wait.");
                    condition.await();
                }
                System.out.println("Thread1 is going ahead to execute.");
            } catch (InterruptedException e){
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    private class Thread2 extends Thread {
        @Override
        public void run() {
            try{
                lock.lock();
                isCompleted = true;
                System.out.println("Thread 2 has completed.. and signalled");
                condition.signal();
            } finally {
                lock.unlock();
            }
        }
    }
}
